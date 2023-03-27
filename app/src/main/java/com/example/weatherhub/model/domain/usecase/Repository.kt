package com.example.weatherhub.model.domain.usecase

import com.example.weatherhub.data.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getRussianWeatherFromLocalStorage(): List<Weather>
    fun getWorldWeatherFromLocalStorage(): List<Weather>
}