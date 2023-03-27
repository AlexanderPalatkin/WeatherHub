package com.example.weatherhub.repository

import com.example.weatherhub.viewmodel.DetailsViewModel
import com.example.weatherhub.viewmodel.HistoryViewModel

interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallBackForAllCities)
}