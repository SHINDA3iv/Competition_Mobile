package com.example.parkingapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.repository.ParkingRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ParkRepository : ParkingRepository {
    private val parkingSpotLD = MutableLiveData<List<ParkingSpotItem>>()
    private val parkingSpotList = sortedSetOf<ParkingSpotItem>({o1, o2 -> o1.spotId.compareTo(o2.spotId)})


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


    override suspend fun getParkingSpotList(): LiveData<List<ParkingSpotItem>> {
        parkingSpotList.addAll(mainApi.getParkingList())
        updateList()

        return parkingSpotLD
    }

    override suspend fun sendParkingSpot(): ParkingSpotItem {
        TODO("Not yet implemented")
    }

    private fun updateList() {
        parkingSpotLD.value = parkingSpotList.toList()
    }

    private companion object {
        const val BASE_URL = "http://192.168.47.225:8081/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }


}