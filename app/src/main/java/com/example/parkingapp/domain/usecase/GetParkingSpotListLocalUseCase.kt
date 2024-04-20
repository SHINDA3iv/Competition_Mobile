package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.repository.ParkingRepository

class GetParkingSpotListLocalUseCase(private val repository: ParkingRepository) {

    operator fun invoke() = repository.getParkingSpotListLocal()
}