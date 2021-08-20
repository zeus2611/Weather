package com.nischay.weather.data

import com.nischay.weather.data.api.RetrofitInstance
import com.nischay.weather.data.model.current.Current
import com.nischay.weather.data.model.Data
import retrofit2.Response

class Repository {
    suspend fun getDataHourlyDaily(options: Map<String, String>): Response<Data>{
        return RetrofitInstance.api.getDataHourlyDaily(options)
    }

    suspend fun getDataCurrent(options: Map<String, String>) : Response<Current>{
        return RetrofitInstance.api.getCurrentData((options))
    }
}