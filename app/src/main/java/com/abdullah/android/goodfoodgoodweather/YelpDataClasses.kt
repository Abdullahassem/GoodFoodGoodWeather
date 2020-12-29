package com.abdullah.android.goodfoodgoodweather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class YelpSearchResult (
    @SerializedName("total") val total:Int,
    @SerializedName("businesses")val restaurants:List<YelpRestaurant>
)

@Entity(tableName = "restaurant_table")
data class YelpRestaurant(
    @PrimaryKey val id: String,
    val name:String? = "",
    val rating:Double? = 0.0,
    val price:String? = "",
    val distance:Double? = 0.0,
    @SerializedName("review_count") val numReviews:Int? = 0,
    @SerializedName("image_url")val imageUrl:String? = "",
    val categories:List<YelpCategory> = listOf(),
    val location:YelpLocation?,
    val coordinates: YelpCoordinates?
)
{
    fun displayDistance():String{
        return "$distance km"
    }
}

data class YelpCategory(
    val title:String?
)

data class YelpLocation(
    val city:String?,
    val address1:String?
)

data class YelpCoordinates(
    val longitude:Double?,
    val latitude:Double?
)
// using yelpLocation for display purposes and using yelpCoordinates for gathering accurate weather data