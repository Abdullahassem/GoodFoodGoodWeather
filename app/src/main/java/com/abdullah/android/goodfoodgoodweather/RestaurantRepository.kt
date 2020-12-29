package com.abdullah.android.goodfoodgoodweather

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.abdullah.android.goodfoodgoodweather.Database.RestaurantDatabase
import java.lang.IllegalStateException


private const val DATABASE_NAME="restaurant-database"

class RestaurantRepository private constructor(context: Context){

    private val database:RestaurantDatabase= Room.databaseBuilder(
        context.applicationContext,RestaurantDatabase::class.java, DATABASE_NAME
    ).build()

    private val restaurantDao=database.restaurantDao()

    fun get():RestaurantRepository{
        return INSTANCE?:
        throw IllegalStateException("repo must be initialized")
    }

    suspend fun insertRestaurants(restaurants: List<YelpRestaurant>) = restaurantDao.insertAll(restaurants)

    fun getRestaurants() = restaurantDao.getRestaurants()

    suspend fun deleteRestaurants() = restaurantDao.deleteAllRestaurants()

    companion object{
        private var INSTANCE:RestaurantRepository?=null

        fun initialize(context: Context){
            if(INSTANCE==null)
                INSTANCE=RestaurantRepository(context)
        }

        fun get(): RestaurantRepository{
            return INSTANCE ?:
                    throw IllegalStateException("Repository must be initialized")
        }
    }

}