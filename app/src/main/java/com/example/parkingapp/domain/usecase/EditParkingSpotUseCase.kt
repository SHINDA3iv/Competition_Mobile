package com.example.parkingapp.domain.usecase

import com.example.parkingapp.domain.entity.ParkingSpotItemLocal
import com.example.parkingapp.domain.repository.ParkingRepository

class EditParkingSpotUseCase(private val repository: ParkingRepository) {

    operator fun invoke(parkingSpotItemLocal: ParkingSpotItemLocal) = repository.editParkingSpot(parkingSpotItemLocal)
}