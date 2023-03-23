package com.example.weatherhub.repository

import com.example.weatherhub.BuildConfig
import com.example.weatherhub.repository.dto.WeatherDTO
import com.example.weatherhub.utils.YANDEX_DOMAIN
import com.example.weatherhub.utils.convertDtoToModel
import com.example.weatherhub.viewmodel.DetailsViewModel
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryRetrofit2Impl : DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack) {
        val weatherAPI = Retrofit.Builder().apply {
            baseUrl(YANDEX_DOMAIN)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherAPI::class.java)
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