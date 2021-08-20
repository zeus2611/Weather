package com.nischay.weather.data.model.current

import com.google.gson.annotations.SerializedName
import com.nischay.weather.data.model.Weather

data class Current(
    @SerializedName("weather")
    val weat: ArrayList<Weather>,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val sys: Sys,
    val name: String
)