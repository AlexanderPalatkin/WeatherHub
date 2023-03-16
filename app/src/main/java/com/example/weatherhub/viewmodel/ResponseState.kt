package com.example.weatherhub.viewmodel

sealed class ResponseState {
    object ResponseOk: ResponseState()
    data class ErrorClient(val error: String):ResponseState()
    data class ErrorServer(val error: String):ResponseState()
}