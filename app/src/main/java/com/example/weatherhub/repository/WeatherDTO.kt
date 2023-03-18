package com.example.weatherhub.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDTO(
    @SerializedName("fact")
    val factDTO: FactDTO,
    @SerializedName("info")
    val infoDTO: InfoDTO
):Parcelable