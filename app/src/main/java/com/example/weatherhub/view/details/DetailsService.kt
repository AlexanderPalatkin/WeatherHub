package com.example.weatherhub.view.details

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.weatherhub.BuildConfig
import com.example.weatherhub.repository.WeatherDTO
import com.example.weatherhub.utils.*
import com.example.weatherhub.viewmodel.ResponseState
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailsService(val name: String = "") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val lat = it.getDoubleExtra(KEY_BUNDLE_LAT, 0.0)
            val lon = it.getDoubleExtra(KEY_BUNDLE_LON, 0.0)
            Log.d("@@@", "DetailsService $lat $lon")

            val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
            val uri = URL(urlText)

            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                    addRequestProperty(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
                }

            val headers = urlConnection.headerFields
            val responseMessage = urlConnection.responseMessage
            val responseCode = urlConnection.responseCode

//                    val serverSide = 500
//                    val clientSide = 400..499
//                    val responseOk = 200..299
//
//                    if (responseCode >= serverSide) {
//                        val errorServer = responseCode.toString()
//                        onServerResponseState.onResponseState(ResponseState.ErrorServer(errorServer))
//                    } else if (responseCode in clientSide) {
//                        val errorClient = responseCode.toString()
//                        onServerResponseState.onResponseState(ResponseState.ErrorClient(errorClient))
//                    } else if (responseCode in responseOk) {
//
//                    }

            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
            val message = Intent(KEY_WAVE_SERVICE_BROADCAST)
            message.putExtra(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER, weatherDTO)
//            sendBroadcast(message)
            LocalBroadcastManager.getInstance(this).sendBroadcast(message)
        }
    }
}