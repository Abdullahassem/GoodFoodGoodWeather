package com.abdullah.android.goodfoodgoodweather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

 interface YelpApi {
    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") authorizationHeader:String,
        @Query("term") searchTerm:String,
        @Query("latitude")lat:Double,
        @Query("longitude")lon:Double
    ):Call<YelpSearchResult>
//
//
//
//    fun searchRestaurants(
//        @Header("Authorization") authorizationHeader:String,
//        @Query("term") searchTerm:String,
//        @Query("location")location:String):Call<YelpSearchResult>

}

