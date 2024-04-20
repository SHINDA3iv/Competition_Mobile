package com.example.parkingapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.parkingapp.domain.entity.ParkingSpotItem

interface ParkingRepository {

    suspend fun getParkingSpotList(): LiveData<List<ParkingSpotItem>>

    suspend fun sendParkingSpot(): ParkingSpotItem
}