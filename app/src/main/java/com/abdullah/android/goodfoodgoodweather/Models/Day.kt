package com.abdullah.android.goodfoodgoodweather.Models

data class Day(
    val avghumidity: Double,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val avgvis_km: Double,
    val avgvis_miles: Double,
    val condition: ConditionX,
    val daily_chance_of_rain: String,
    val daily_chance_of_snow: String,
    val daily_will_it_rain: Int,
    val daily_will_it_snow: Int,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val maxwind_kph: Double,
    val maxwind_mph: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val totalprecip_in: Double,
    val totalprecip_mm: Double,
    val uv: Double
)