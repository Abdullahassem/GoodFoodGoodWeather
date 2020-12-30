package com.abdullah.android.goodfoodgoodweather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.Internal.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG="MainActivity"
private const val BASE_URL= "https://api.yelp.com/v3/"
private const val API_KEY="C269WVIARBTOJVG8q8hOs7sUj3Awf94kHQZiPbqBwm1mQU3w3dkwLKYROPzSFPb8HNp71C7MIKxlKqY3cJAKnPtxBu2Vb-dzqmDaWnIo8ZgoscelaDY1RUzboN7ZX3Yx"






class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        val lon=intent.getDoubleExtra("longitude",0.0)
        val lat=intent.getDoubleExtra("latitude",0.0)
        val term=intent.getStringExtra("term")
        val restaurants= mutableListOf<YelpRestaurant>()
        val adapter=RestaurantsAdapter(this,restaurants)
        rvRestaurants.adapter=adapter
        rvRestaurants.layoutManager=LinearLayoutManager(this)




        if(networkCheck()) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val yelpApi = retrofit.create(YelpApi::class.java)
            if (term != null) {
                yelpApi.searchRestaurants("Bearer $API_KEY", term, lat, lon)
                    .enqueue(object : Callback<YelpSearchResult> {


                        override fun onResponse(
                            call: Call<YelpSearchResult>,
                            response: Response<YelpSearchResult>
                        ) {
                            val body = response.body()
                            if (body == null) {
                                Log.w(TAG, "did not recieve body")
                                return
                            }
                            restaurants.addAll(body.restaurants)
                            viewModel.deleteRestaurant()
                            viewModel.insertRestaurants(body.restaurants)
                            adapter.notifyDataSetChanged()
                        }

                        override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                            Log.i(TAG, "onFailure $t")
                        }

                    })
            }
        }else{
            viewModel.restaurantsLiveData().observe(this){
                rvRestaurants.adapter = RestaurantsAdapter(this, it)
            }
        }
    }

    fun networkCheck(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            return true
        } else {
            Toast.makeText(this, "no internet", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}