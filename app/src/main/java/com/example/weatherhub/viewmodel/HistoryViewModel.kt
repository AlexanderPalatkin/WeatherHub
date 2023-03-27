package com.example.weatherhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherhub.repository.DetailsRepositoryRoomImpl
import com.example.weatherhub.repository.Weather

class HistoryViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRepositoryRoomImpl = DetailsRepositoryRoomImpl()
) :

    ViewModel() {
    fun getData(): LiveData<AppState> = liveData

    fun getAll() {
        repository.getAllWeatherDetails(object : CallBackForAllCities {
            override fun onResponse(listWeather: List<Weather>) {
                liveData.postValue(AppState.Success(listWeather))
            }

            override fun onFail(throwable: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    interface CallBackForAllCities {
        fun onResponse(listWeather: List<Weather>)

        fun onFail(throwable: Throwable)
    }
}