package com.abdullah.android.goodfoodgoodweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.abdullah.android.goodfoodgoodweather.Models.WeatherResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL_WEATHER= "https://api.weatherapi.com/v1/"
private const val API_KEY_WEATHER="615b2c0757484776824113744201612"


class WeatherInfo : AppCompatActivity() {

 private lateinit var location:TextView
    private var latitude:Double= 0.0
    private var longitude:Double=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

    location=findViewById(R.id.location)
        latitude=intent.getDoubleExtra("latitude",0.0)
        longitude=intent.getDoubleExtra("longitude",0.0)

        location.text=intent.getStringExtra("location")
//        location.text="latitude: $latitude, longitude:$longitude"
//        var latlon = "${latitude.toString()},${longitude.toString()}"
        var latlon="$latitude,$longitude"


        val retrofit2= Retrofit.Builder().baseUrl(BASE_URL_WEATHER).client(OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()
        val weatherApi= retrofit2.create(WeatherApi::class.java)

        var s: Call<WeatherResponse> = weatherApi.getWeather("NYC")

        s.enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                println("TestWeather: ${response.body()?.location?.country.toString()}")
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })





    }
}