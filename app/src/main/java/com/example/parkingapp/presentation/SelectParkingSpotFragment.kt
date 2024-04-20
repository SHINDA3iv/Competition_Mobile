package com.example.parkingapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parkingapp.R
import com.example.parkingapp.databinding.FragmentSelectParkingSpotBinding
import com.example.parkingapp.domain.entity.LevelItem


class SelectParkingSpotFragment : Fragment() {
    private var _binding: FragmentSelectParkingSpotBinding? = null
    private val binding
        get() = _binding!!
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var adapter: LevelItemAdapter

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
                adapter.submitList(levelList)
            }
            parkingSpotList.observe(viewLifecycleOwner) {parkingSpotList ->
                Log.i("MyLog", parkingSpotList.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = LevelItemAdapter()
        binding.rvLevel.adapter = adapter

        setupClickListener()
    }

    private fun setupClickListener() {
        adapter.onLevelItemClickListener = {level ->
            mainViewModel.editLevelItem(level)
            mainViewModel.getParkingSpotList(level.level)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}