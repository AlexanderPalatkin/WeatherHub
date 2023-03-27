package com.example.weatherhub.view.historyList

import com.example.weatherhub.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}