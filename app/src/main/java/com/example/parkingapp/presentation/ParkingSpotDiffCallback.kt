package com.example.parkingapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal

class ParkingSpotDiffCallback : DiffUtil.ItemCallback<ParkingSpotItemLocal>() {
    override fun areItemsTheSame(
        oldItem: ParkingSpotItemLocal,
        newItem: ParkingSpotItemLocal
    ): Boolean {
        return oldItem.spotId == newItem.spotId
    }

    override fun areContentsTheSame(
        oldItem: ParkingSpotItemLocal,
        newItem: ParkingSpotItemLocal
    ): Boolean {
        return oldItem == newItem
    }


}