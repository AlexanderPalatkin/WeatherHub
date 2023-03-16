package com.example.weatherhub.repository

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}