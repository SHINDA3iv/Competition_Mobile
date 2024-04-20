package com.example.parkingapp.data.remote

import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal
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
    private val parkingSpotList = sortedSetOf<ParkingSpotItemLocal>({o1, o2 -> o1.position.compareTo(o2.position)})
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



    override suspend fun getParkingSpotList(level: Int): List<ParkingSpotItemLocal> {
        parkingSpotList.clear()
        val list = mainApi.getParkingSpotList(level).map { ParkingSpotItemLocal(
            spotId = it.spotId,
            level = it.level,
            position = it.position,
            isBusy = it.isBusy,
            isSelect = false
        ) }
        parkingSpotList.addAll(list)

        return parkingSpotList.toList()
    }

    override fun getLevelItem(levelItemId: Int): LevelItem {
        return levelList.find { it.id == levelItemId }
            ?: throw RuntimeException("Element with id $levelItemId not found")
    }

    override fun getParkingSpotItem(parkingSpotId: Long): ParkingSpotItemLocal {
        return parkingSpotList.find { it.spotId == parkingSpotId }
            ?: throw throw RuntimeException("Element with id $parkingSpotId not found")
    }

    override fun addLevelItem(levelItem: LevelItem) {
        levelList.add(levelItem)
    }

    override fun addParkingSpotItem(parkingSpotItemLocal: ParkingSpotItemLocal) {
        parkingSpotList.add(parkingSpotItemLocal)
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

    override fun getLevelListLocal() = levelList.toList()
    override fun getParkingSpotListLocal() = parkingSpotList.toList()
    override fun editLevelItem(levelItem: LevelItem) {
        val oldElement = getLevelItem(levelItem.id)
        levelList.remove(oldElement)
        changeColorLevel()
        addLevelItem(levelItem)
    }

    override fun editParkingSpot(parkingSpotItemLocal: ParkingSpotItemLocal) {
        val oldElement = getParkingSpotItem(parkingSpotItemLocal.spotId)
        parkingSpotList.remove(oldElement)
        changeColorParkingSpot()
        addParkingSpotItem(parkingSpotItemLocal)

    }

    private fun changeColorLevel() {
        levelList.map { it.select = false }
    }

    private fun changeColorParkingSpot() {
        parkingSpotList.map { it.isSelect = false }
    }

    suspend fun sendBookParkingSpot(body: BookParkingSpot): String {
        return mainApi.bookParkingSpot(body)[KEY_BOOK]!!
    }

    private companion object {
        const val BASE_URL = "http://192.168.74.225:8081/"
        const val KEY_LEVEL = "max_level"
        const val KEY_BOOK = "message"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }


}