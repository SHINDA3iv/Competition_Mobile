package com.example.parkingapp.data.remote

import com.example.parkingapp.domain.entity.ParkingSpotItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("api/spots/levels")
    suspend fun getCountLevel(): Map<String, Int>

    @GET("api/spots")
    suspend fun getParkingSpotList(@Query("level") level: Int = LEVEL): List<ParkingSpotItem>

    private companion object {
        const val LEVEL = 1
    }
}