package com.example.parkingapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingapp.data.remote.ParkingRepositoryImpl
import com.example.parkingapp.data.remote.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = ParkingRepositoryImpl()

    private val _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token

    fun login(username: String, password: String) {
        val user = User(username, password)

        viewModelScope.launch(Dispatchers.IO) {
            _token.postValue(repository.loginUser(user))
        }

    }
}