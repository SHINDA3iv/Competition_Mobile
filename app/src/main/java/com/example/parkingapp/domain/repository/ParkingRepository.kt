package com.example.parkingapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal

interface ParkingRepository {
    suspend fun getParkingSpotList(level: Int): List<ParkingSpotItemLocal>

    fun getLevelItem(levelItemId: Int): LevelItem

    fun getParkingSpotItem(parkingSpotId: Long): ParkingSpotItemLocal


    fun addLevelItem(levelItem: LevelItem)

    fun addParkingSpotItem(parkingSpotItemLocal: ParkingSpotItemLocal)

    suspend fun getLevelList(): List<LevelItem>

    fun getLevelListLocal(): List<LevelItem>

    fun getParkingSpotListLocal(): List<ParkingSpotItemLocal>

    fun editLevelItem(levelItem: LevelItem)

    fun editParkingSpot(parkingSpotItemLocal: ParkingSpotItemLocal)
}