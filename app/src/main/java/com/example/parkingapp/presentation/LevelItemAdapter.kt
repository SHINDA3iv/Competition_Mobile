package com.example.parkingapp.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.parkingapp.R
import com.example.parkingapp.databinding.ItemLevelChoosenBinding
import com.example.parkingapp.databinding.ItemLevelUnselectedBinding
import com.example.parkingapp.domain.entity.LevelItem
import java.lang.RuntimeException

class LevelItemAdapter : ListAdapter<LevelItem, LevelItemViewHolder>(LevelItemDiffCallback()){
    var onLevelItemClickListener: ((LevelItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when(viewType) {
            VIEW_TYPE_SELECT -> ItemLevelChoosenBinding.inflate(inflater, parent, false)
            VIEW_TYPE_UNSELECT -> ItemLevelUnselectedBinding.inflate(inflater, parent, false)
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        return LevelItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LevelItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item.select) {
            VIEW_TYPE_SELECT
        } else {
            VIEW_TYPE_UNSELECT
        }
    }

    companion object {
        const val VIEW_TYPE_SELECT = 0
        const val VIEW_TYPE_UNSELECT = 1
    }
}