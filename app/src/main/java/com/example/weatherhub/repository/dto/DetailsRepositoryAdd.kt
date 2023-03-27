package com.example.weatherhub.repository.dto

import com.example.weatherhub.repository.Weather

interface DetailsRepositoryAdd {
    fun addWeather(weather: Weather)
}