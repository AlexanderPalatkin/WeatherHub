package com.example.weatherhub.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherhub.data.*
import com.example.weatherhub.model.data.repository.DetailsRepositoryRetrofit2Impl
import com.example.weatherhub.model.data.repository.DetailsRepositoryRoomImpl
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryAdd
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryOne

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private var repositoryAdd: DetailsRepositoryAdd = DetailsRepositoryRoomImpl()
) : ViewModel() {

    private var repositoryOne: DetailsRepositoryOne = DetailsRepositoryRetrofit2Impl()

    fun getLiveData() = liveData

    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repositoryOne = if (isInternet()) {
            DetailsRepositoryRetrofit2Impl()
        } else {
            DetailsRepositoryRoomImpl()
        }
        repositoryOne.getWeatherDetails(city, object : CallBack {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
                if (isInternet()) {
                    repositoryAdd.addWeather(weather)
                }
            }

            override fun onFail(throwable: Throwable) {
                liveData.postValue(DetailsState.Error(throwable))
            }
        })
    }

    private fun isInternet(): Boolean {
        return true
    }

    interface CallBack {
        fun onResponse(weather: Weather)

        fun onFail(throwable: Throwable)
    }

}