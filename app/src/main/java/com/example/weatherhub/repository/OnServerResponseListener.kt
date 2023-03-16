package com.example.weatherhub.repository

import com.example.weatherhub.viewmodel.ResponseState

fun interface OnServerResponseListener {
    fun onResponseState(responseState: ResponseState)
}