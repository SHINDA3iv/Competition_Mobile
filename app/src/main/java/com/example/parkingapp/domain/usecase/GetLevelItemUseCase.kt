package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.repository.ParkingRepository

class GetLevelItemUseCase(private val repository: ParkingRepository) {

    operator fun invoke(levelItemId: Int) = repository.getLevelItem(levelItemId)
}