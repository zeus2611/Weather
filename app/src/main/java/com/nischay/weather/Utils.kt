package com.nischay.weather

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("HH:mm")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}