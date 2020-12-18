package com.abdullah.android.goodfoodgoodweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG="MainActivity"
private const val BASE_URL= "https://api.yelp.com/v3/"
private const val API_KEY="C269WVIARBTOJVG8q8hOs7sUj3Awf94kHQZiPbqBwm1mQU3w3dkwLKYROPzSFPb8HNp71C7MIKxlKqY3cJAKnPtxBu2Vb-dzqmDaWnIo8ZgoscelaDY1RUzboN7ZX3Yx"
private const val WEATHER_API_KEY="615b2c0757484776824113744201612"






class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurants= mutableListOf<YelpRestaurant>()
        val adapter=RestaurantsAdapter(this,restaurants)
        rvRestaurants.adapter=adapter
        rvRestaurants.layoutManager=LinearLayoutManager(this)



        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val yelpApi= retrofit.create(YelpApi::class.java)
        yelpApi.searchRestaurants("Bearer $API_KEY","food","Amsterdam").enqueue(object :Callback<YelpSearchResult>{

            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i(TAG,"onResponse $response")
                val body=response.body()
                if(body==null){
                    Log.w(TAG,"did not recieve body")
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }

        })
    }

}