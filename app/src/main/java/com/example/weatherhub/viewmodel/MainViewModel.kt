package com.example.weatherhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherhub.model.domain.entity.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
) :

    ViewModel() {
    fun getData(): LiveData<AppState> = liveData

    fun getWeatherRussian() = getWeather(true)
    fun getWeatherWorld() = getWeather(false)

    private fun getWeather(isRussian: Boolean) = Thread {
        liveData.postValue(AppState.Loading)
        val answer =
            if (!isRussian) repository.getWorldWeatherFromLocalStorage() else repository.getRussianWeatherFromLocalStorage()
        liveData.postValue(AppState.Success(answer))
        if (repository.getWorldWeatherFromLocalStorage()
                .isEmpty() && repository.getRussianWeatherFromLocalStorage().isEmpty()
        ) {
            liveData.postValue(AppState.Error(Throwable()))
        }
    }.start()
}