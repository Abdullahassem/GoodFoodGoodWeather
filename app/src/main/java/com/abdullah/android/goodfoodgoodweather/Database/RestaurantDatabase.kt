package com.abdullah.android.goodfoodgoodweather.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdullah.android.goodfoodgoodweather.YelpRestaurant


@Database(entities = [ YelpRestaurant::class],version = 1)
@TypeConverters(RestaurantTypeConverters::class)
abstract class RestaurantDatabase:RoomDatabase() {
    abstract fun restaurantDao():RestaurantDao
}