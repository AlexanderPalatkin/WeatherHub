package com.example.weatherhub.repository

import com.example.weatherhub.viewmodel.DetailsViewModel

interface DetailsRepositoryOne {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack)
}