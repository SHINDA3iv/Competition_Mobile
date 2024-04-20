package com.example.parkingapp.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.parkingapp.R
import com.example.parkingapp.databinding.FragmentCustomDialogBinding

class CustomDialogFragment: DialogFragment() {
    private var _binding: FragmentCustomDialogBinding? = null
    private val binding
        get() = _binding!!
    private var onSubmit: (() -> Unit)? = null
    private var onCancel: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.fragment_custom_dialog, null)
        _binding = FragmentCustomDialogBinding.bind(dialogView)
        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)

        // Настройка слушателей для кнопок Cancel и Submit
        binding.cancelButton.setOnClickListener {
            onCancel?.invoke() // Вызов функции обратного вызова для отмены
            dismiss()
        }
        binding.submitButton.setOnClickListener {
            onSubmit?.invoke() // Вызов функции обратного вызова для подтверждения
            dismiss()
        }

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}