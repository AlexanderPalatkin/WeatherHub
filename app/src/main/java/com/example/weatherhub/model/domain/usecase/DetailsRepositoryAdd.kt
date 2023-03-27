package com.example.weatherhub.model.domain.usecase

import com.example.weatherhub.data.Weather

interface DetailsRepositoryAdd {
    fun addWeather(weather: Weather)
}