package com.example.parkingapp.data.remote

import com.example.parkingapp.domain.entity.ParkingSpotItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface MainApi {

    @GET("api/spots/levels")
    suspend fun getCountLevel(): Map<String, Int>

    @GET("api/spots")
    suspend fun getParkingSpotList(@Query("level") level: Int = LEVEL): List<ParkingSpotItem>



    @PATCH("api/spots/set")
    suspend fun bookParkingSpot(@Body bookParkingSpot: BookParkingSpot): Map<String, String>

    private companion object {
        const val LEVEL = 1
    }
}