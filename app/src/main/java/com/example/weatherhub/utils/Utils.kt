package com.example.weatherhub.utils

import com.example.weatherhub.repository.dto.FactDTO
import com.example.weatherhub.repository.Weather
import com.example.weatherhub.repository.dto.WeatherDTO
import com.example.weatherhub.repository.getDefaultCity

const val KEY_BUNDLE_WEATHER = "key"
const val YANDEX_API_KEY = "X-Yandex-API-Key"
const val YANDEX_DOMAIN = "https://api.weather.yandex.ru/"
const val YANDEX_ENDPOINT = "v2/informers?"
const val KEY_BUNDLE_LAT = "lat"
const val KEY_BUNDLE_LON = "lon"
const val KEY_BUNDLE_SERVICE_BROADCAST_WEATHER = "weather"
const val KEY_WAVE_SERVICE_BROADCAST = "myaction_way"

class Utils {
}

fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: FactDTO = weatherDTO.factDTO
    return (Weather(getDefaultCity(), fact.temperature, fact.feelsLike, fact.icon, fact.condition))
}