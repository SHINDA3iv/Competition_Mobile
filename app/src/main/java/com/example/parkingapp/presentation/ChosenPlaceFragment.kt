package com.example.parkingapp.presentation

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parkingapp.R
import com.example.parkingapp.data.remote.BookParkingSpot
import com.example.parkingapp.data.remote.UserImpl
import com.example.parkingapp.databinding.FragmentChosenPlaceBinding
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ChosenPlaceFragment : Fragment() {
    private var _binding: FragmentChosenPlaceBinding? = null
    private val binding
        get() = _binding!!
    private var parkingSpot: BookParkingSpot? = null
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChosenPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

        binding.confirmButton.setOnClickListener {
            mainViewModel.bookParkingSpot(BookParkingSpot(UserImpl.level, UserImpl.position, !UserImpl.isBusy, UserImpl.userId))
            UserImpl.level = -1
            UserImpl.position = -1
            UserImpl.isBusy = false

            binding.contentDataSpot.visibility = View.INVISIBLE
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setData() {
        if (UserImpl.isBusy) {
            binding.dataText.text = getCurrentDate()
            binding.placeText.text = "${UserImpl.level} этаж\n${UserImpl.position} место"
        } else {
            binding.contentDataSpot.isVisible = false
        }
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    companion object {
        private const val KEY_PARKING_SPOT = "parking_spot"
    }
}