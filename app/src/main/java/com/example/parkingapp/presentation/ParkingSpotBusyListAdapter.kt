package com.example.parkingapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingapp.R
import com.example.parkingapp.databinding.ItemBusyParkingSpotBinding
import com.example.parkingapp.domain.entity.ParkingSpotItemLocal

class ParkingSpotBusyListAdapter : RecyclerView.Adapter<ParkingSpotBusyListAdapter.ParkingSpotBusyViewHolder>() {
    var parkingSpotBusyList: List<ParkingSpotItemLocal> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ParkingSpotBusyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBusyParkingSpotBinding.bind(view)

        fun bind(item: ParkingSpotItemLocal) {
            binding.tvLevel.text = "${item.position} место"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParkingSpotBusyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_busy_parking_spot, parent, false)

        return ParkingSpotBusyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ParkingSpotBusyViewHolder,
        position: Int
    ) {
        val item = parkingSpotBusyList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return parkingSpotBusyList.size
    }
}