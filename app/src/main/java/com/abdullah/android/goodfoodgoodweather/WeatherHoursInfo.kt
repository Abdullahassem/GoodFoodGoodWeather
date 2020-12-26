package com.abdullah.android.goodfoodgoodweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdullah.android.goodfoodgoodweather.Models.Hour
import com.abdullah.android.goodfoodgoodweather.databinding.ActivityWeatherHoursInfoBinding
import kotlin.math.log


class WeatherHoursInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityWeatherHoursInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val arrayList = ArrayList<Hour>()
        var list =intent.getParcelableArrayListExtra<Hour>("info")
        list?.forEach { arrayList.add(it) }

        binding.hourRecycler.layoutManager= GridLayoutManager(this,3)
        binding.hourRecycler.adapter = HoursAdapter(arrayList)

    }
}