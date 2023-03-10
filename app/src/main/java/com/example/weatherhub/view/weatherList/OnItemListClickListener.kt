package com.example.weatherhub.view.weatherList

import com.example.weatherhub.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}