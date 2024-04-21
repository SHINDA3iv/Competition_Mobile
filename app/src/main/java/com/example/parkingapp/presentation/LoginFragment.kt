package com.example.parkingapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.parkingapp.R
import com.example.parkingapp.data.remote.UserImpl
import com.example.parkingapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!
    private val viewModelValidation by lazy {
        ViewModelProvider(this)[CorrectValidationViewModel::class.java]
    }
    private val loginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }


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

        addTextChangeListeners()
        observerViewModel()

        binding.confirmButton.setOnClickListener {
            validateLogin()
        }
        binding.signinLink.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_signinFragment,
                null,
                navOptions {
                    popUpTo(R.id.loginFragment) {
                        inclusive = true
                    }
                })

        }
    }

    private fun validateLogin() {
        val validate = viewModelValidation.validateInput(
            binding.eTextMail.text.toString(),
            binding.eTextPassword.text.toString()
        )

        if (validate) {
            with(binding) {

                loginViewModel.login(eTextMail.text.toString(), eTextPassword.text.toString())


                loginViewModel.token.observe(viewLifecycleOwner) {
                    if (it != "") {
                        findNavController().navigate(R.id.action_loginFragment_to_contentActivity)
                        requireActivity().finish()
                    } else {
                        binding.layoutMail.error = "Неправльная почта"
                        binding.layoutPassword.error = "Неправильный пароль"
                    }
                }

            }
        }
    }

    private fun observerViewModel() {
        viewModelValidation.errorInputEmail.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Неправильный email"
            } else {
                null
            }
            binding.layoutMail.error = message
        }
        viewModelValidation.errorInputPassword.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Неправильный пароль"
            } else {
                null
            }
            binding.layoutPassword.error = message
        }
        loginViewModel.token.observe(viewLifecycleOwner) {
            UserImpl.token = it

            findNavController().navigate(R.id.action_loginFragment_to_contentActivity)
            requireActivity().finish()
        }
    }


    private fun addTextChangeListeners() {
        binding.eTextMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModelValidation.resetErrorInputEmail()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.eTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModelValidation.resetErrorInputPassword()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


}