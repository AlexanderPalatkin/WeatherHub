package com.example.weatherhub.model.domain.usecase

import com.example.weatherhub.ui.history.HistoryViewModel

interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallBackForAllCities)
}