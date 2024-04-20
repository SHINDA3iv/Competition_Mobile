package com.example.parkingapp.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.parkingapp.R
import com.example.parkingapp.data.remote.BookParkingSpot
import com.example.parkingapp.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment() {
    private var _binding: FragmentCustomDialogBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var parkingSpot: BookParkingSpot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.fragment_custom_dialog, null)
        _binding = FragmentCustomDialogBinding.bind(dialogView)
        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)

        binding.conformityText.text = "${parkingSpot?.level} этаж ${parkingSpot?.position} место"

        // Настройка слушателей для кнопок Cancel и Submit
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.submitButton.setOnClickListener {

            parkingSpot?.let { body -> viewModel.bookParkingSpot(body) }
            dismiss()
        }

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseParam() {
        val args = requireArguments()

        parkingSpot = args.getSerializable(SCREEN_MODE) as BookParkingSpot
    }

    companion object {
        const val TAG = "CustomDialogFragment"
        private const val SCREEN_MODE = "screen_mode"

        fun newInstance(bookParkingSpot: BookParkingSpot): CustomDialogFragment {
            return CustomDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCREEN_MODE, bookParkingSpot)
                }
            }
        }
    }
}