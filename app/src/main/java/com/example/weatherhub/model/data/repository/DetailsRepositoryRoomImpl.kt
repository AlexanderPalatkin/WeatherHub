package com.example.weatherhub.model.data.repository

import com.example.weatherhub.app.MyApp
import com.example.weatherhub.data.City
import com.example.weatherhub.data.Weather
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryAdd
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryAll
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryOne
import com.example.weatherhub.utils.converterHistoryEntityToWeather
import com.example.weatherhub.utils.converterWeatherToHistoryEntity
import com.example.weatherhub.ui.details.DetailsViewModel
import com.example.weatherhub.ui.history.HistoryViewModel

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