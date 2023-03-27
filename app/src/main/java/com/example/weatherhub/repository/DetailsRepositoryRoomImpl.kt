package com.example.weatherhub.repository

import com.example.weatherhub.app.MyApp
import com.example.weatherhub.repository.dto.DetailsRepositoryAdd
import com.example.weatherhub.utils.converterHistoryEntityToWeather
import com.example.weatherhub.utils.converterWeatherToHistoryEntity
import com.example.weatherhub.viewmodel.DetailsViewModel
import com.example.weatherhub.viewmodel.HistoryViewModel

class DetailsRepositoryRoomImpl : DetailsRepositoryOne, DetailsRepositoryAll, DetailsRepositoryAdd {
    override fun getAllWeatherDetails(callback: HistoryViewModel.CallBackForAllCities) {
        callback.onResponse(converterHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))
    }

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack) {
        val list =
            converterHistoryEntityToWeather(MyApp.getHistoryDao().getHistoryForCity(city.name))
        if (list.isEmpty()) {
            callback.onFail(Throwable())
        } else {
            callback.onResponse(list.last())
        }

    }

    override fun addWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(converterWeatherToHistoryEntity(weather))
    }
}