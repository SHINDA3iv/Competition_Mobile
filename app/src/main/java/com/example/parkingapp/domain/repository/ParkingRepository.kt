package com.example.parkingapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem

interface ParkingRepository {

    suspend fun getParkingSpotList(): List<ParkingSpotItem>

    suspend fun getLevelList(): List<LevelItem>

    suspend fun sendParkingSpot(): ParkingSpotItem
}