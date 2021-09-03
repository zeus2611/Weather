package com.nischay.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nischay.weather.data.Repository
import com.nischay.weather.data.model.current.Current
import com.nischay.weather.data.model.Data
import com.nischay.weather.util.Constants.Companion.icon
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
//    var icon: HashMap<String, Int> = HashMap()

    private val _dataHourlyDaily = MutableLiveData<Response<Data>>()
    val dataHourlyDaily:LiveData<Response<Data>>
        get() = _dataHourlyDaily

    private val _dataCurrent = MutableLiveData<Response<Current>>()
    val dataCurrent:LiveData<Response<Current>>
        get() = _dataCurrent

    fun getDataCurrent(options: Map<String, String>) {
        Log.d("MainActivity", "Getting Current Data")
        viewModelScope.launch {
            val response = repository.getDataCurrent(options)
            _dataCurrent.value = response
        }
    }

    fun getDataHourlyDaily(options: Map<String, String>) {
        Log.d("MainActivity", "Getting Hourly & Daily Data From API")
        viewModelScope.launch {
            val response = repository.getDataHourlyDaily(options)
            _dataHourlyDaily.value = response
            Log.d("MainActivity", response.message())
        }
    }

    fun icons() {
        icon["01d"] = R.drawable.w01d
        icon["01n"] = R.drawable.w01n
        icon["02d"] = R.drawable.w02d
        icon["02n"] = R.drawable.w02n
        icon["03d"] = R.drawable.w03d
        icon["03n"] = R.drawable.w03n
        icon["04d"] = R.drawable.w04d
        icon["04n"] = R.drawable.w04n
        icon["09d"] = R.drawable.w09d
        icon["09n"] = R.drawable.w09n
        icon["10d"] = R.drawable.w10d
        icon["10n"] = R.drawable.w10n
        icon["11d"] = R.drawable.w11d
        icon["11n"] = R.drawable.w11n
        icon["13d"] = R.drawable.w13d
        icon["13n"] = R.drawable.w13n
        icon["50d"] = R.drawable.w50d
        icon["50n"] = R.drawable.w50n
    }

}