package com.example.weatherhub.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getRusWeatherFromLocalStorage(): List<Weather>
    fun getWorldWeatherFromLocalStorage(): List<Weather>
}