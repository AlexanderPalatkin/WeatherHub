package com.example.weatherhub.viewmodel

import com.example.weatherhub.repository.Weather

sealed class AppState {
    object Loading:AppState()
    data class Success(val weatherList: List<Weather>):AppState()
    data class Error(val error: Throwable):AppState()
}