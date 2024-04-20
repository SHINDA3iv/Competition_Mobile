package com.example.parkingapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingapp.databinding.ItemBusyParkingSpotBinding
import com.example.parkingapp.databinding.ItemFreeParkingSpotBinding
import com.example.parkingapp.databinding.ItemSelectedParkingSpotBinding
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal
import java.lang.RuntimeException

class ParkingSpotListAdapter : ListAdapter<ParkingSpotItemLocal, ParkingSpotViewHolder>(ParkingSpotDiffCallback()) {
    var onParkingSpotClickListener: ((ParkingSpotItemLocal) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingSpotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when(viewType) {
            VIEW_TYPE_FREE -> ItemFreeParkingSpotBinding.inflate(inflater, parent, false)
            VIEW_TYPE_SELECTED -> ItemSelectedParkingSpotBinding.inflate(inflater, parent, false)
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        return ParkingSpotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParkingSpotViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        holder.itemView.setOnClickListener {
            onParkingSpotClickListener?.invoke(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item.isSelect) {
            VIEW_TYPE_SELECTED
        } else {
            VIEW_TYPE_FREE
        }
    }

    private companion object {
        const val VIEW_TYPE_FREE = 0
        const val VIEW_TYPE_SELECTED = 1
    }
}