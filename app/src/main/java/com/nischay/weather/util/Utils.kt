package com.nischay.weather.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("HH:mm")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
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

    fun speedMtoKM(){}

    fun mTokm() {

    }
}