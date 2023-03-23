package com.example.weatherhub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherhub.repository.*

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private var repository: DetailsRepository = DetailsRepositoryRetrofit2Impl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city, object : CallBack {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
            }

            override fun onFail(throwable: Throwable) {
                liveData.postValue(DetailsState.Error(throwable))
            }
        })
    }

    interface CallBack {
        fun onResponse(weather: Weather)

        fun onFail(throwable: Throwable)
    }
}