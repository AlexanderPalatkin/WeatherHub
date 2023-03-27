package com.example.weatherhub.ui.weatherList

import com.example.weatherhub.data.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}