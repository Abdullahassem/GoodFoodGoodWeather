package com.abdullah.android.goodfoodgoodweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.android.goodfoodgoodweather.Models.Hour
import com.abdullah.android.goodfoodgoodweather.databinding.HoursWeatherInfoBinding

class HoursAdapter(private val hoursList: ArrayList<Hour>) : RecyclerView.Adapter<HoursAdapter.HoursViewHolder>() {

    inner class HoursViewHolder(val binding: HoursWeatherInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hour: Hour) {
            binding.temp.text = hour.temp_c.toString()
            binding.time.text=hour.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder(HoursWeatherInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        holder.bind(hoursList[position])
    }

    override fun getItemCount(): Int {
        return hoursList.size
    }
}