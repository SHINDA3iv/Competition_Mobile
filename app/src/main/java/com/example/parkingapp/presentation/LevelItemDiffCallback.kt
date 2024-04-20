package com.example.parkingapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.parkingapp.domain.entity.LevelItem

class LevelItemDiffCallback : DiffUtil.ItemCallback<LevelItem>() {
    override fun areItemsTheSame(oldItem: LevelItem, newItem: LevelItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LevelItem, newItem: LevelItem): Boolean {
        return oldItem == newItem
    }

}