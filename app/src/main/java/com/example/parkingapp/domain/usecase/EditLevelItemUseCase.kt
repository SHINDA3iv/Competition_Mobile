package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.repository.ParkingRepository

class EditLevelItemUseCase(private val repository: ParkingRepository) {

    operator fun invoke(levelItem: LevelItem) = repository.editLevelItem(levelItem)
}