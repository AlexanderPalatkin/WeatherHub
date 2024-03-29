package com.example.weatherhub.model.data.repository

import com.example.weatherhub.BuildConfig
import com.example.weatherhub.data.City
import com.example.weatherhub.model.data.dto.WeatherDTO
import com.example.weatherhub.model.domain.usecase.DetailsRepositoryOne
import com.example.weatherhub.utils.YANDEX_API_KEY
import com.example.weatherhub.utils.YANDEX_DOMAIN
import com.example.weatherhub.utils.YANDEX_ENDPOINT
import com.example.weatherhub.utils.convertDtoToModel
import com.example.weatherhub.ui.details.DetailsViewModel
import com.google.gson.Gson
import okhttp3.*

class DetailsRepositoryOkHttpImpl : DetailsRepositoryOne {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.CallBack) {
        val client = OkHttpClient()
        val builder = Request.Builder()
        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
        builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=${city.lat}&lon=${city.lon}")
        val request = builder.build()
        val call = client.newCall(request)
        Thread {
            val response = call.execute()
            if (response.isSuccessful) {
                val serverResponse = response.body()!!.string()
                val weatherDTO: WeatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                val weather = convertDtoToModel(weatherDTO)
                weather.city = city
                callback.onResponse(weather)
            } else {

            }
        }.start()
    }
}