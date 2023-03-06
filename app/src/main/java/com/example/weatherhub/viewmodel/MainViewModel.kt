package com.example.weatherhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherhub.repository.RepositoryImpl
import java.io.IOError
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
) :

    ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeather() {

        liveData.postValue(AppState.Loading)
        Thread {
            sleep(2000L)

            liveData.postValue(AppState.Success(repository.getWeatherFromServer()))

//                liveData.postValue(AppState.Error(IllegalAccessError()))

        }.start()
    }
}