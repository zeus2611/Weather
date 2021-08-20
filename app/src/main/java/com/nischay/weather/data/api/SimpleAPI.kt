package com.nischay.weather.data.api

import com.nischay.weather.data.model.current.Current
import com.nischay.weather.data.model.Data
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.QueryMap

interface SimpleAPI {

    @GET("data/2.5/onecall")
    suspend fun getDataHourlyDaily(
        @QueryMap option: Map<String, String>
    ): Response<Data>

    @GET("data/2.5/weather")
    suspend fun getCurrentData(
        @QueryMap option: Map<String, String>
    ): Response<Current>
}