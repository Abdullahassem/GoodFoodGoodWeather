package com.abdullah.android.goodfoodgoodweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val TAG="MainActivity"
private const val BASE_URL= "https://api.yelp.com/v3/"
private const val API_KEY="C269WVIARBTOJVG8q8hOs7sUj3Awf94kHQZiPbqBwm1mQU3w3dkwLKYROPzSFPb8HNp71C7MIKxlKqY3cJAKnPtxBu2Vb-dzqmDaWnIo8ZgoscelaDY1RUzboN7ZX3Yx"
private const val WEATHER_API_KEY="615b2c0757484776824113744201612"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}