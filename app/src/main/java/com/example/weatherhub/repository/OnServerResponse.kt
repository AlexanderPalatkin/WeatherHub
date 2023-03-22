package com.example.weatherhub.repository

import com.example.weatherhub.repository.dto.WeatherDTO

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}