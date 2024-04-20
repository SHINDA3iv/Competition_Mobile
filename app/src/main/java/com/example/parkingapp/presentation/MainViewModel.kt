package com.example.parkingapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkingapp.data.remote.ParkingRepositoryImpl
import com.example.parkingapp.domain.entity.LevelItem
import com.example.parkingapp.domain.entity.ParkingSpotItem
import com.example.parkingapp.domain.usecase.EditLevelItemUseCase
import com.example.parkingapp.domain.usecase.GetLevelItemUseCase
import com.example.parkingapp.domain.usecase.GetLevelListLocalUseCase
import com.example.parkingapp.domain.usecase.GetLevelListUseCase
import com.example.parkingapp.domain.usecase.GetParkingSpotListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = ParkingRepositoryImpl()

    private val getLevelListUseCase = GetLevelListUseCase(repository)
    private val getParkingSpotListUseCase = GetParkingSpotListUseCase(repository)
    private val getLevelListLocalUseCase = GetLevelListLocalUseCase(repository)
    private val editLevelItemUseCase = EditLevelItemUseCase(repository)


    private var _parkingSpotList = MutableLiveData<List<ParkingSpotItem>>()
    val parkingSpotList: LiveData<List<ParkingSpotItem>>
        get() = _parkingSpotList

    private val _levelList = MutableLiveData<List<LevelItem>>()
    val levelList: LiveData<List<LevelItem>>
        get() = _levelList



    fun getLevelList() {
        viewModelScope.launch(Dispatchers.IO) {
            _levelList.postValue(getLevelListUseCase())
        }
    }

    fun getParkingSpotList(level: Int = LEVEL) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getParkingSpotListUseCase(level)
            _parkingSpotList.postValue(list)
        }
    }

    fun editLevelItem(levelItem: LevelItem) {
        val newItem = levelItem.copy(select = !levelItem.select)
        editLevelItemUseCase(newItem)
        _levelList.value = getLevelListLocalUseCase()
    }

    private companion object {
        const val LEVEL = 1
    }
}