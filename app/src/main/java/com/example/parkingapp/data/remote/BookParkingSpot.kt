package com.example.parkingapp.data.remote

import java.io.Serializable

data class BookParkingSpot(
    val level: Int,
    val position: Int,
    val isBusy: Boolean,
    val user_id: Long
) : Serializable
