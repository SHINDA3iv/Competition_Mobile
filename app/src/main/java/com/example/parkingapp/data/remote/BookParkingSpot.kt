package com.example.parkingapp.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookParkingSpot(
    val level: Int,
    val position: Int,
    val isBusy: Boolean,
    val userId: Long
) : Serializable
