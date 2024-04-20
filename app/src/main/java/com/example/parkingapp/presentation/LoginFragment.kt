package com.example.parkingapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.parkingapp.R
import com.example.parkingapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_contentActivity)
            requireActivity().finish()
        }
        binding.signinLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signinFragment, null, navOptions {
                popUpTo(R.id.loginFragment) {
                    inclusive = true
                }
            })

        }
    }

}