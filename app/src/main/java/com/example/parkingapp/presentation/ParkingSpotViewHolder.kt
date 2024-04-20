package com.example.parkingapp.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.parkingapp.databinding.ItemBusyParkingSpotBinding
import com.example.parkingapp.databinding.ItemFreeParkingSpotBinding
import com.example.parkingapp.databinding.ItemSelectedParkingSpotBinding
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal

class ParkingSpotViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parkingSpotItem: ParkingSpotItemLocal) {
        when(binding) {
            is ItemFreeParkingSpotBinding -> {
                binding.tvLevel.text = "${parkingSpotItem.position} место"
            }
            is  ItemSelectedParkingSpotBinding -> {
                binding.tvLevel.text = "${parkingSpotItem.position} место"
            }
        }
    }
}