package com.abdullah.android.goodfoodgoodweather

import com.google.gson.annotations.SerializedName

data class YelpSearchResult (
    @SerializedName("total") val total:Int,
    @SerializedName("businesses")val restaurants:List<YelpRestaurant>
)

data class YelpRestaurant(
    val name:String,
    val rating:Double,
    val price:String,
    val distance:Double,
    @SerializedName("review_count") val numReviews:String,
    @SerializedName("image_url")val imageUrl:String,
    val categories:List<YelpCategory>,
    val location:YelpLocation
)
{
    fun displayDistance():String{
        return "$distance km"
    }
}

data class YelpCategory(
    val title:String
)

data class YelpLocation(
    val city:String,
    val address:String
)