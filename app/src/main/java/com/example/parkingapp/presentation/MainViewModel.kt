package com.example.parkingapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingapp.data.remote.ParkingRepositoryImpl
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.usecase.GetLevelListUseCase
import com.example.parkingapp.domain.usecase.GetParkingSpotListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = ParkingRepositoryImpl()

    private val getParkingSpotListUseCase = GetParkingSpotListUseCase(repository)
    private val getLevelListUseCase = GetLevelListUseCase(repository)


    private val _parkingSpotList = MutableLiveData<List<ParkingSpotItem>>()
    val parkingSpotList: LiveData<List<ParkingSpotItem>>
        get() = _parkingSpotList

    private val _levelList = MutableLiveData<List<LevelItem>>()
    val levelList: LiveData<List<LevelItem>>
        get() = _levelList


    fun getParkingSpotList() {
        viewModelScope.launch(Dispatchers.IO) {
            _parkingSpotList.postValue(getParkingSpotListUseCase())
        }
    }

    fun getLevelList() {
        viewModelScope.launch(Dispatchers.IO) {
            _levelList.postValue(getLevelListUseCase())
        }
    }

}