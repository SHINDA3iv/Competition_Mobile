package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.repository.ParkingRepository

class GetLevelListLocalUseCase(private val repository: ParkingRepository) {

    operator fun invoke() = repository.getLevelListLocal()
}