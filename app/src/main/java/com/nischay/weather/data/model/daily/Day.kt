package com.nischay.weather.data.model.daily

import com.nischay.weather.data.model.Weather

data class Day(
    val dt:Long,
    val sunrise:Long,
    val sunset:Long,
    val moonrise:Long,
    val moonset:Long,
    val temp: Temp,
    val pressure: Int,
    val humidity: Int,
    val wind_speed: Double,
    val weather: ArrayList<Weather>
)