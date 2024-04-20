package com.example.parkingapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parkingapp.R
import com.example.parkingapp.data.remote.BookParkingSpot
import com.example.parkingapp.databinding.FragmentSelectParkingSpotBinding
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem


class SelectParkingSpotFragment : Fragment() {
    private var _binding: FragmentSelectParkingSpotBinding? = null
    private val binding
        get() = _binding!!
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var adapterLevel: LevelItemAdapter
    private lateinit var adapterParkingSpot: ParkingSpotListAdapter
    private lateinit var adapterParkingSpotBusy: ParkingSpotBusyListAdapter
    private var parkingSpot: BookParkingSpot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelectParkingSpotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observerViewModel()

        binding.btnSelectParkingSpot.setOnClickListener {
            val dialogFragment = parkingSpot?.let { mess -> CustomDialogFragment.newInstance(mess) }
            dialogFragment?.show(childFragmentManager, CustomDialogFragment.TAG)
        }
    }

    private fun loadData() {
        with(mainViewModel) {
            getLevelList()
            getParkingSpotList()
        }
    }

    private fun observerViewModel() {
        with(mainViewModel) {
            levelList.observe(viewLifecycleOwner) { levelList ->
                adapterLevel.submitList(levelList)
            }
            parkingSpotList.observe(viewLifecycleOwner) {parkingSpotList ->
                adapterParkingSpot.submitList(parkingSpotList.filter { !it.isBusy })

                adapterParkingSpotBusy.parkingSpotBusyList = parkingSpotList.filter { it.isBusy }
            }
        }
    }

    private fun setupRecyclerView() {
        adapterLevel = LevelItemAdapter()
        binding.rvLevel.adapter = adapterLevel

        adapterParkingSpot = ParkingSpotListAdapter()
        binding.rvListFreeSpot.adapter = adapterParkingSpot

        adapterParkingSpotBusy = ParkingSpotBusyListAdapter()
        binding.rvListBusySpot.adapter = adapterParkingSpotBusy

        setupClickListener()
    }

    private fun setupClickListener() {
        adapterLevel.onLevelItemClickListener = {level ->
            mainViewModel.editLevelItem(level)
            mainViewModel.getParkingSpotList(level.level)
        }
        adapterParkingSpot.onParkingSpotClickListener = {parkingSpotItemLocal ->
            mainViewModel.editParkingSpot(parkingSpotItemLocal)
            parkingSpot = BookParkingSpot(
                level = parkingSpotItemLocal.level,
                position = parkingSpotItemLocal.position.toInt(),
                isBusy = true,
                1
            )
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}