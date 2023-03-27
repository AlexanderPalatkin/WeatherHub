package com.example.weatherhub.model.domain.entity

import com.example.weatherhub.data.Weather
import com.example.weatherhub.data.getRussianCities
import com.example.weatherhub.data.getWorldCities
import com.example.weatherhub.model.domain.usecase.Repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather = Weather()
    override fun getWorldWeatherFromLocalStorage(): List<Weather> = getWorldCities()
    override fun getRussianWeatherFromLocalStorage(): List<Weather> = getRussianCities()
}