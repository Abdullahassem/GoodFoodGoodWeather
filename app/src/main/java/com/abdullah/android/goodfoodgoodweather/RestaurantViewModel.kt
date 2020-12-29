package com.abdullah.android.goodfoodgoodweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantViewModel :ViewModel(){
    private val restaurantRepository = RestaurantRepository.get()

    fun restaurantsLiveData(): LiveData<List<YelpRestaurant>> = restaurantRepository.getRestaurants()

    fun deleteRestaurant() = viewModelScope.launch {
        restaurantRepository.deleteRestaurants()
    }

    fun insertRestaurants(restaurant: List<YelpRestaurant>) = viewModelScope.launch {
        restaurantRepository.insertRestaurants(restaurant)
    }


}