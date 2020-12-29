package com.abdullah.android.goodfoodgoodweather

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantsAdapter(val context: Context,val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant,parent,false))

    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant) {


            itemView.name.text=restaurant.name
            itemView.ratingBar.rating=restaurant.rating?.toFloat()!!
            itemView.numReviews.text="${restaurant.numReviews}"
            itemView.address.text=restaurant.location?.address1
            itemView.category.text=restaurant.categories[0].title
            itemView.distance.text=restaurant.displayDistance()
            itemView.price.text=restaurant.price



            Glide.with(context).load(restaurant.imageUrl)
                .override(300,300)
                .centerCrop()
                .circleCrop()
                .into(itemView.imageView)

            itemView.cardView.setOnClickListener {
                var intent = Intent (context,WeatherInfo::class.java)
                intent.putExtra("latitude",restaurant.coordinates?.latitude)
                intent.putExtra("longitude",restaurant.coordinates?.longitude)
                intent.putExtra("location",restaurant.location?.city)
                context.startActivity(intent)
            }


        }
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant=restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount()=restaurants.size

}
