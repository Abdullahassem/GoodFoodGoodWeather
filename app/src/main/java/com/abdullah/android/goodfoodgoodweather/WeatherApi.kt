package com.abdullah.android.goodfoodgoodweather

import com.abdullah.android.goodfoodgoodweather.Models.WeatherResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json?")
    fun getWeather(
        @Query("q") loc: String,
        @Query("key") key: String = "615b2c0757484776824113744201612",
        @Query("days") days:String = "3"
        ): Call<WeatherResponse>

}

