package com.nischay.weather.util

import java.text.SimpleDateFormat
import kotlin.math.round

object Utils {
    fun getDay(epoch: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEEE")

        return simpleDateFormat.format(epoch).toString()
    }

    fun getDate(epoch: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd LLL.")

        return simpleDateFormat.format(epoch).toString()
    }

    fun getTime(epoch: Long): String {
        val simpleDateFormat = SimpleDateFormat("KK:mm aaa")

        return simpleDateFormat.format(epoch).toString()
    }

    fun tempKtoC(temp: Double): Int {
        val tempC =  (temp - 273.15)

        return if(tempC - tempC.toInt() < 0.5){
            tempC.toInt()
        }else {
            tempC.toInt() + 1
        }
    }

    fun tempKtoF(temp: Double): Int {
        val tempF =  ((temp - 273.15) * 9/5) + 32

        return if(tempF - tempF.toInt() < 0.5){
            tempF.toInt()
        }else {
            tempF.toInt() + 1
        }
    }

    fun mToKm(distance: Int): Int {
        return distance/1000
    }

    fun hPaToBar(pressure: Int): Float {
        return round((pressure/1000.0)).toFloat()
    }
}