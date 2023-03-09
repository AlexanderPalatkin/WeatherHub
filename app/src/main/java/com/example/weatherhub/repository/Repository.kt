package com.example.weatherhub.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getRussianWeatherFromLocalStorage(): List<Weather>
    fun getWorldWeatherFromLocalStorage(): List<Weather>
}