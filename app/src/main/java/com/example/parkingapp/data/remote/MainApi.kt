package com.example.parkingapp.data.remote

import com.example.parkingapp.domain.entity.ParkingSpotItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface MainApi {

    @GET("api/spots/levels")
    suspend fun getCountLevel(@Header("Authorization") token: String): Map<String, Int>

    @GET("api/spots")
    suspend fun getParkingSpotList(
        @Header("Authorization") token: String,
        @Query("level") level: Int = LEVEL
    ): List<ParkingSpotItem>

    @PATCH("api/spots/set")
    suspend fun bookParkingSpot(@Body bookParkingSpot: BookParkingSpot): Map<String, String>

    @POST("auth/sign-in")
    fun loginUser(@Body user: User): Call<TokenResponse>

    @POST("api/complains/add")
    suspend fun addComplain(@Body complain: Complain): Map<String, String>


    private companion object {
        const val LEVEL = 1
    }
}