package com.abdullah.android.goodfoodgoodweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.abdullah.android.goodfoodgoodweather.Models.Hour
import com.abdullah.android.goodfoodgoodweather.Models.WeatherResponse
import com.abdullah.android.goodfoodgoodweather.databinding.ActivityMainBinding
import com.abdullah.android.goodfoodgoodweather.databinding.ActivityWeatherInfoBinding
//import kotlinx.android.synthetic.main.activity_weather_info.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL_WEATHER= "https://api.weatherapi.com/v1/"


class WeatherInfo : AppCompatActivity() {

    private var latitude:Double= 0.0
    private var longitude:Double=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding= ActivityWeatherInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        latitude=intent.getDoubleExtra("latitude",0.0)
        longitude=intent.getDoubleExtra("longitude",0.0)

        var latlon="$latitude,$longitude"


        val retrofit2= Retrofit.Builder().baseUrl(BASE_URL_WEATHER).client(OkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build()
        val weatherApi= retrofit2.create(WeatherApi::class.java)

        var s: Call<WeatherResponse> = weatherApi.getWeather(latlon)

        s.enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {

                binding.day.text="${response.body()?.forecast?.forecastday?.get(0)?.date}"
                binding.temp.text="${response.body()?.current?.temp_c.toString()}°"
                binding.condition.text="${response.body()?.forecast?.forecastday?.get(0)?.hour?.get(0)?.condition?.text}"

                binding.day2.text="${response.body()?.forecast?.forecastday?.get(1)?.date}"
                binding.temp2.text="${response.body()?.forecast?.forecastday?.get(1)?.day?.avgtemp_c}°"
                binding.condition2.text="${response.body()?.forecast?.forecastday?.get(1)?.day?.condition?.text}"

                binding.day3.text="${response.body()?.forecast?.forecastday?.get(2)?.date}"
                binding.temp3.text="${response.body()?.forecast?.forecastday?.get(2)?.day?.avgtemp_c}°"
                binding.condition3.text="${response.body()?.forecast?.forecastday?.get(2)?.day?.condition?.text}"

                fun toArray(position:Int): ArrayList<Hour> {
                val arrayList: ArrayList<Hour> = arrayListOf()
                response.body()?.forecast?.forecastday?.get(position)?.hour?.forEach {
                    arrayList.add(it)
                }
                return arrayList
                }


                binding.cv.setOnClickListener {
                    var intent = Intent (this@WeatherInfo,WeatherHoursInfo::class.java)
                    intent.putParcelableArrayListExtra("info",toArray(0))
                    this@WeatherInfo.startActivity(intent)
                }
                binding.cv2.setOnClickListener {
                    var intent = Intent (this@WeatherInfo,WeatherHoursInfo::class.java)
                    intent.putParcelableArrayListExtra("info",toArray(1))
                    this@WeatherInfo.startActivity(intent)
                }
                binding.cv3.setOnClickListener {
                    var intent = Intent (this@WeatherInfo,WeatherHoursInfo::class.java)
                    intent.putParcelableArrayListExtra("info",toArray(2))
                    this@WeatherInfo.startActivity(intent)
                }




            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })



    }
}