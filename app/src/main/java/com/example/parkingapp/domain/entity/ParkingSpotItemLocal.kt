package com.example.parkingapp.domain.entity

data class ParkingSpotItemLocal(
    val spotId: Long,
    val level: Int,
    val position: Long,
    val isBusy: Boolean,
    var isSelect: Boolean
//    val userId: Long
)
