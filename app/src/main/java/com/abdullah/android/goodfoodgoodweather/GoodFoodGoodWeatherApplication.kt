package com.abdullah.android.goodfoodgoodweather

import android.app.Application

class GoodFoodGoodWeatherApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        RestaurantRepository.initialize(this)
    }
}