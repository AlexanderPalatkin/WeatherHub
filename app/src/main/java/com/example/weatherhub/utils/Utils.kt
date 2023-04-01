package com.example.weatherhub.utils

import com.example.weatherhub.model.data.room.HistoryEntity
import com.example.weatherhub.data.City
import com.example.weatherhub.model.data.dto.FactDTO
import com.example.weatherhub.data.Weather
import com.example.weatherhub.model.data.dto.WeatherDTO
import com.example.weatherhub.data.getDefaultCity

const val KEY_BUNDLE_WEATHER = "KEY_BUNDLE_WEATHER"
const val IS_WORLD_KEY = "LIST_OF_TOWNS_KEY"
const val YANDEX_API_KEY = "X-Yandex-API-Key"
const val YANDEX_DOMAIN = "https://api.weather.yandex.ru/"
const val YANDEX_ENDPOINT = "v2/informers?"
const val KEY_BUNDLE_LAT = "lat"
const val KEY_BUNDLE_LON = "lon"
const val REQUEST_PERMISSION_CONTACTS_CODE = 42
const val REQUEST_PERMISSION_LOCATION_CODE = 43
const val REFRESH_PERIOD = 60000L
const val MINIMAL_DISTANCE = 100f

class Utils {
}

fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: FactDTO = weatherDTO.factDTO
    return (Weather(getDefaultCity(), fact.temperature, fact.feelsLike, fact.icon, fact.condition))
}

fun converterHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, it.feelsLike, it.icon, it.condition)
    }
}

fun converterWeatherToHistoryEntity(weather: Weather): HistoryEntity {
    return HistoryEntity(
        0,
        weather.city.name,
        weather.temperature,
        weather.feelsLike,
        weather.icon,
        weather.condition
    )
}