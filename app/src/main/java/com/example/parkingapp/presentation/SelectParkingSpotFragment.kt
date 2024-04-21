package com.example.parkingapp.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.os.postDelayed
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.parkingapp.R
import com.example.parkingapp.data.remote.BookParkingSpot
import com.example.parkingapp.data.remote.UserImpl
import com.example.parkingapp.databinding.FragmentSelectParkingSpotBinding
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.google.android.material.snackbar.Snackbar


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
    private lateinit var gestureDetector: GestureDetector
    private var currentLevel: Int? = null
    private val handler = Handler(Looper.getMainLooper())
    private val runnableCode = object : Runnable {
        override fun run() {
            // Поместите ваш код здесь, который вы хотите повторять каждые 5 секунд
            // Например:
            // doSomething()
            mainViewModel.getParkingSpotList(currentLevel ?: 1)

            // Планируем повторение через 5 секунд
            handler.postDelayed(this, 2000) // 5000 миллисекунд = 5 секунд
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
        handler.postDelayed(runnableCode, 2000)
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
            if (!UserImpl.isBusy) {

                mainViewModel.bookParkingSpot(parkingSpot!!)
                UserImpl.level = parkingSpot?.level!!
                UserImpl.position = parkingSpot?.level!!
                UserImpl.isBusy = true
                val sb = Snackbar.make(binding.scrSelectSpots, "Ваша место занято", Snackbar.LENGTH_SHORT)
                sb.show()
            } else {
                val sb = Snackbar.make(binding.scrSelectSpots, "Вы уже заняли место", Snackbar.LENGTH_SHORT)
                sb.show()
            }

        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnableCode)
        super.onDestroy()

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
            parkingSpotList.observe(viewLifecycleOwner) { parkingSpotList ->
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
        adapterLevel.onLevelItemClickListener = { level ->
            mainViewModel.editLevelItem(level)
            mainViewModel.getParkingSpotList(level.level)
            currentLevel = level.level
        }
        adapterParkingSpot.onParkingSpotClickListener = { parkingSpotItemLocal ->
            mainViewModel.editParkingSpot(parkingSpotItemLocal)

            Log.i("MyLog", "${UserImpl.level} ${UserImpl.position}")
            parkingSpot = BookParkingSpot(
                level = parkingSpotItemLocal.level,
                position = parkingSpotItemLocal.position.toInt(),
                isBusy = true,
                UserImpl.userId
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_PARKING_SPOT = "parking_spot"
    }

}