package com.example.weatherhub.model.data.repository

import com.example.weatherhub.BuildConfig
import com.example.weatherhub.data.City
import com.example.weatherhub.model.data.dto.WeatherDTO
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryOne
import com.example.weatherhub.model.domain.usecase.WeatherAPI
import com.example.weatherhub.utils.YANDEX_DOMAIN
import com.example.weatherhub.utils.convertDtoToModel
import com.example.weatherhub.ui.details.DetailsViewModel
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryRetrofit2Impl : DetailsRepositoryOne {
    private val weatherAPI = Retrofit.Builder().apply {
        baseUrl(YANDEX_DOMAIN)
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    }.build().create(WeatherAPI::class.java)

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack) {
        weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY, city.lat, city.lon)
            .enqueue(object : Callback<WeatherDTO> {
                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val weather = convertDtoToModel(it)
                            weather.city = city
                            callback.onResponse(weather)
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                    callback.onFail(t)
                }
            })
    }
}