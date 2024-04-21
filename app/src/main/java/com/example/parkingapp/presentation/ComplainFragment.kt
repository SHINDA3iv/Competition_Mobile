package com.example.parkingapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.parkingapp.R
import com.example.parkingapp.data.remote.ParkingRepositoryImpl
import com.example.parkingapp.databinding.FragmentComplainBinding
import kotlinx.coroutines.launch


class ComplainFragment : Fragment() {
    private var _binding: FragmentComplainBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComplainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnComplain.setOnClickListener {
            viewModel.addComplain(binding.etComplain.text.toString())
        }
        viewModel.exit.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

}