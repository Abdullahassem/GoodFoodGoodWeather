package com.abdullah.android.goodfoodgoodweather.Models

data class WeatherResponse(
    val alert: Alert,
    val current: Current,
    val forecast: Forecast,
    val location: Location
)