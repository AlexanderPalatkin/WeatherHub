package com.example.weatherhub.repository

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("fact")
    val factDTO: FactDTO,
    @SerializedName("info")
    val infoDTO: InfoDTO
)