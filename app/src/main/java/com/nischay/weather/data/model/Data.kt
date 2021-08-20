package com.nischay.weather.data.model

import com.nischay.weather.data.model.daily.Day
import com.nischay.weather.data.model.hourly.Hour

data class Data(
    val hourly: ArrayList<Hour>,
    val daily: ArrayList<Day>
)
