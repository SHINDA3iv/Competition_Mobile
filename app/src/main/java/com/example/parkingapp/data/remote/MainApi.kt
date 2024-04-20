package com.example.parkingapp.data.remote

import com.example.parkingapp.domain.entity.ParkingSpotItem
import retrofit2.http.GET

interface MainApi {
    @GET("api/spots")
    suspend fun getParkingList(): List<ParkingSpotItem>

    @GET("api/spots/levels")
    suspend fun getCountLevel(): Map<String, Int>
}