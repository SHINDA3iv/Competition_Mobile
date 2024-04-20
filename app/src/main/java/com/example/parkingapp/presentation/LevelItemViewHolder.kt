package com.example.parkingapp.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.parkingapp.databinding.ItemLevelChoosenBinding
import com.example.parkingapp.databinding.ItemLevelUnselectedBinding
import com.example.parkingapp.domain.entity.LevelItem

class LevelItemViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(levelItem: LevelItem) {
        when(binding) {
            is ItemLevelChoosenBinding -> {
                binding.tvLevel.text = "Этаж ${levelItem.level}"
            }
            is ItemLevelUnselectedBinding -> {
                binding.tvLevel.text = "Этаж ${levelItem.level}"
            }
        }
    }


}