package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.repository.ParkingRepository

class GetParkingSpotListUseCase(private val repository: ParkingRepository) {

    suspend operator fun invoke(level: Int) = repository.getParkingSpotList(level)
}