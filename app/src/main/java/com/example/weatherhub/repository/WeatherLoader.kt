package com.example.weatherhub.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.weatherhub.BuildConfig
import com.example.weatherhub.repository.dto.WeatherDTO
import com.example.weatherhub.utils.YANDEX_API_KEY
import com.example.weatherhub.utils.YANDEX_DOMAIN
import com.example.weatherhub.utils.YANDEX_ENDPOINT
import com.example.weatherhub.viewmodel.ResponseState
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(
    private val onServerResponseListener: OnServerResponse,
    private val onServerResponseState: OnServerResponseListener
) {
    fun loadWeather(lat: Double, lon: Double) {

        val urlText = "$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=$lat&lon=$lon"
        val uri = URL(urlText)

        Thread {
            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                    addRequestProperty(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
                }
            try {
                val headers = urlConnection.headerFields
                val responseMessage = urlConnection.responseMessage
                val responseCode = urlConnection.responseCode

                val serverSide = 500
                val clientSide = 400..499
                val responseOk = 200..299

                if (responseCode >= serverSide) {
                    val errorServer = responseCode.toString()
                    onServerResponseState.onResponseState(ResponseState.ErrorServer(errorServer))
                } else if (responseCode in clientSide) {
                    val errorClient = responseCode.toString()
                    onServerResponseState.onResponseState(ResponseState.ErrorClient(errorClient))
                } else if (responseCode in responseOk) {
                    val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                    Handler(Looper.getMainLooper()).post {
                        onServerResponseListener.onResponse(
                            weatherDTO
                        )
                    }
                }
            } catch (ej: JsonSyntaxException) {
                Log.d("@@@", "Fail JsonSyntax", ej)
                ej.printStackTrace()
            } catch (e: Exception) {
                Log.d("@@@", "Fail connection", e)
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        }.start()
    }
}