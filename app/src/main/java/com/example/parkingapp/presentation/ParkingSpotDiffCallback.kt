package com.example.parkingapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal

class ParkingSpotDiffCallback : DiffUtil.ItemCallback<ParkingSpotItem>() {
    override fun areItemsTheSame(oldItem: ParkingSpotItem, newItem: ParkingSpotItem): Boolean {
        return oldItem.spotId == newItem.spotId
    }

    override fun areContentsTheSame(oldItem: ParkingSpotItem, newItem: ParkingSpotItem): Boolean {
        return oldItem == newItem
    }


}