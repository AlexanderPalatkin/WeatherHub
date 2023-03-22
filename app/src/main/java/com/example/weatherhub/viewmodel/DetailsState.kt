package com.example.weatherhub.viewmodel

import com.example.weatherhub.repository.Weather

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val weather: Weather) : DetailsState()
    data class Error(val error: Throwable) : DetailsState()
}