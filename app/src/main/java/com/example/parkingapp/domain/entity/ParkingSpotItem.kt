package com.example.parkingapp.domain.entity

data class ParkingSpotItem(
    val spotId: Long,
    val level: Int,
    val position: Long,
    val isBusy: Boolean,
    val userId: Long
)
