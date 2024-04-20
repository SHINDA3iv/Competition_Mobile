package com.example.parkingapp.data.remote

import androidx.lifecycle.MutableLiveData
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.repository.ParkingRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

class ParkingRepositoryImpl :
    ParkingRepository {
    private val parkingSpotList = sortedSetOf<ParkingSpotItem>({o1, o2 -> o1.spotId.compareTo(o2.spotId)})
    private val levelList = sortedSetOf<LevelItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    private fun interceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor())
            .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val mainApi by lazy {
        retrofit.create(MainApi::class.java)
    }


    override suspend fun getParkingSpotList(): List<ParkingSpotItem> {
        parkingSpotList.addAll(mainApi.getParkingList())

        return parkingSpotList.toList()
    }

    override suspend fun getLevelList(): List<LevelItem>  {
        val numLevel = mainApi.getCountLevel()[KEY_LEVEL] ?: throw RuntimeException("Can't find value by $KEY_LEVEL")

        for (i in 0..<numLevel) {
            if (i == 0) {
                levelList.add(LevelItem(
                    i,
                    i + 1,
                    true
                ))
            } else {
                levelList.add(LevelItem(
                    i,
                    i + 1,
                    false
                ))
            }
        }

        return levelList.toList()
    }

    override suspend fun sendParkingSpot(): ParkingSpotItem {
        TODO("Not yet implemented")
    }


    private companion object {
        const val BASE_URL = "http://192.168.47.225:8081/"
        const val KEY_LEVEL = "max_level"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }


}