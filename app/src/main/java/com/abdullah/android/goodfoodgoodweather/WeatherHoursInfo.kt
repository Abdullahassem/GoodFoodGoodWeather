package com.abdullah.android.goodfoodgoodweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
<<<<<<< Updated upstream
=======
import androidx.recyclerview.widget.GridLayoutManager
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        Log.d("TAG","${list?.get(0)?.temp_c} !!!!!!")

        binding.hourRecycler.layoutManager = LinearLayoutManager(this)
=======

        binding.hourRecycler.layoutManager= GridLayoutManager(this,3)
>>>>>>> Stashed changes
        binding.hourRecycler.adapter = HoursAdapter(arrayList)

    }
}