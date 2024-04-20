package com.example.parkingapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem

interface ParkingRepository {
    suspend fun getParkingSpotList(level: Int): List<ParkingSpotItem>

    fun getLevelItem(levelItemId: Int): LevelItem

    fun addLevelItem(levelItem: LevelItem)

    suspend fun getLevelList(): List<LevelItem>

    fun getLevelListLocal(): List<LevelItem>

    fun editLevelItem(levelItem: LevelItem)
}