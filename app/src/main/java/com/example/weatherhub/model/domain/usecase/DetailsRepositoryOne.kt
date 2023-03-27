package com.example.weatherhub.model.domain.usecase

import com.example.weatherhub.data.City
import com.example.weatherhub.viewmodel.DetailsViewModel

interface DetailsRepositoryOne {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack)
}