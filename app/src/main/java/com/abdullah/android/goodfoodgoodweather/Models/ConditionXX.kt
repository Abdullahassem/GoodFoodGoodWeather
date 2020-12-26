package com.abdullah.android.goodfoodgoodweather.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConditionXX(
    val code: Int,
    val icon: String,
    val text: String
) : Parcelable