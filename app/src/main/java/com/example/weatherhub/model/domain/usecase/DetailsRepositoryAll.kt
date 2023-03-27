package com.example.weatherhub.model.domain.usecase

import com.example.weatherhub.viewmodel.HistoryViewModel

interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallBackForAllCities)
}