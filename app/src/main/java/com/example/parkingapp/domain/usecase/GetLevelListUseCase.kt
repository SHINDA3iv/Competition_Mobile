package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.repository.ParkingRepository

class GetLevelListUseCase(private val repository: ParkingRepository) {

    suspend operator fun invoke() = repository.getLevelList()
}