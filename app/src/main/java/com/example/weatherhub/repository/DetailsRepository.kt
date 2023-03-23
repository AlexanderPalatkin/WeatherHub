package com.example.weatherhub.repository

import com.example.weatherhub.viewmodel.DetailsViewModel

interface DetailsRepository {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack)
}