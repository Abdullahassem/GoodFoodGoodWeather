package com.abdullah.android.goodfoodgoodweather.Database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.abdullah.android.goodfoodgoodweather.Models.Hour
import com.abdullah.android.goodfoodgoodweather.YelpCategory
import com.abdullah.android.goodfoodgoodweather.YelpCoordinates
import com.abdullah.android.goodfoodgoodweather.YelpLocation
import com.google.gson.Gson
import java.util.*

class RestaurantTypeConverters
{

    @TypeConverter
    fun gsonToCoordinates(json: String?): YelpCoordinates? {
        return Gson().fromJson(json, YelpCoordinates::class.java)
    }
    @TypeConverter
    fun coordinatesToGson(coordinates: YelpCoordinates): String? {
        return Gson().toJson(coordinates)
    }
    @TypeConverter
    fun gsonToCategory(json: String?): List<YelpCategory>? {
        return Gson().fromJson(json, Array<YelpCategory>::class.java).toList()
    }
    @TypeConverter
    fun categoryToGson(category: List<YelpCategory>): String? {
        return Gson().toJson(category)
    }
    @TypeConverter
    fun gsonToLocation(json: String?): YelpLocation? {
        return Gson().fromJson(json, YelpLocation::class.java)
    }
    @TypeConverter
    fun locationToGson(location: YelpLocation): String? {
        return Gson().toJson(location)
    }
    @TypeConverter
    fun gsonToStringList(json: String?): List<String>? {
        return Gson().fromJson(json, Array<String>::class.java)?.toList() ?: listOf()
    }
    @TypeConverter
    fun StringListToGson(stringList: List<String>?): String? {
        return Gson().toJson(stringList)
    }
    @TypeConverter
    fun gsonToHour(json: String?): List<Hour>? {
        return Gson().fromJson(json, Array<Hour>::class.java)?.toList() ?: listOf()
    }
    @TypeConverter
    fun hourToGson(hour: List<Hour>?): String? {
        return Gson().toJson(hour)
    }

}