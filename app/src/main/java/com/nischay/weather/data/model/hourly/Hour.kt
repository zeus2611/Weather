package com.nischay.weather.data.model.hourly

import com.nischay.weather.data.model.Weather

data class Hour(
    val dt: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val visibility: Int,
    val wind_speed: Double,
    val weather: ArrayList<Weather>
)