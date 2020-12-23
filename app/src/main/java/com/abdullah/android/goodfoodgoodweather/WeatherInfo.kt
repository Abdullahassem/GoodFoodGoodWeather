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
import kotlinx.android.synthetic.main.activity_weather_info.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL_WEATHER= "https://api.weatherapi.com/v1/"


class WeatherInfo : AppCompatActivity() {

//    private lateinit var day:TextView
//    private lateinit var temp:TextView
//    private lateinit var condition:TextView
//    private lateinit var secondDay:TextView
//    private lateinit var secondTemp:TextView
//    private lateinit var secondCondition:TextView
//    private lateinit var thirdDay:TextView
//    private lateinit var thirdTemp:TextView
//    private lateinit var thirdCondition:TextView
//    private lateinit var cv:CardView
//    private lateinit var cv2:CardView
//    private lateinit var cv3:CardView
//

    private var latitude:Double= 0.0
    private var longitude:Double=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding= ActivityWeatherInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.

//        day=findViewById(R.id.day)
//        temp=findViewById(R.id.temp)
//        condition=findViewById(R.id.condition)
//        secondDay=findViewById(R.id.day2)
//        secondTemp=findViewById(R.id.temp2)
//        secondCondition=findViewById(R.id.condition2)
//        thirdDay=findViewById(R.id.day3)
//        thirdTemp=findViewById(R.id.temp3)
//        thirdCondition=findViewById(R.id.condition3)


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
                println("TestWeather: ${response.body()?.location?.country.toString()}")
                println("Forecast: ${response.body()?.forecast.toString()}")
//                day.text=response.body()?.forecast.forecastday.
                day.text="${response.body()?.forecast?.forecastday?.get(0)?.date}"
                temp.text="${response.body()?.current?.temp_c.toString()}°"
                condition.text="${response.body()?.forecast?.forecastday?.get(0)?.hour?.get(0)?.condition?.text}"

                binding.day2.text="${response.body()?.forecast?.forecastday?.get(1)?.date}"
                binding.temp2
                binding.condition2.text="${response.body()?.forecast?.forecastday?.get(1)?.hour?.get(1)?.condition?.text}"
//                secondDay.text="${response.body()?.forecast?.forecastday?.get(1)?.date}"
//                secondTemp.text="${response.body()?.forecast?.forecastday?.get(1)?.day?.avgtemp_c}°"
//                secondCondition.text="${response.body()?.forecast?.forecastday?.get(1)?.hour?.get(1)?.condition?.text}"
//
//                thirdDay.text="${response.body()?.forecast?.forecastday?.get(2)?.date}"
//                thirdTemp.text="${response.body()?.forecast?.forecastday?.get(2)?.day?.avgtemp_c}°"
//                thirdCondition.text="${response.body()?.forecast?.forecastday?.get(2)?.hour?.get(2)?.condition?.text}"

                val arrayList: ArrayList<Hour> = arrayListOf()
                response.body()?.forecast?.forecastday?.get(0)?.hour?.forEach {
                    arrayList.add(it)
                }

                binding.cv.setOnClickListener {
                    var intent = Intent (this@WeatherInfo,WeatherHoursInfo::class.java)
                    intent.putParcelableArrayListExtra("info", arrayList)
                    this@WeatherInfo.startActivity(intent)
                }


            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })





    }
}