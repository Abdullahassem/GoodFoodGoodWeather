package com.abdullah.android.goodfoodgoodweather.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abdullah.android.goodfoodgoodweather.YelpRestaurant

@Dao
interface RestaurantDao {

    @Insert
    suspend fun insertAll(restaurant: List<YelpRestaurant>)

    @Query("Delete FROM restaurant_table")
    suspend fun deleteAllRestaurants()

    @Query("SELECT * FROM restaurant_table")
    fun getRestaurants():LiveData<List<YelpRestaurant>>



}