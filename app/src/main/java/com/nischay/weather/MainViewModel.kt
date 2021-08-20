package com.nischay.weather

import android.location.Location
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import com.google.android.gms.location.FusedLocationProviderClient
import com.nischay.weather.data.Repository
import com.nischay.weather.data.model.current.Current
import com.nischay.weather.data.model.Data
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    var locationPermissionGranted = false
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var lastKnownLocation: Location? = null
    var icon: HashMap<String, String> = HashMap()

    private val _loc = MutableLiveData<Location>()
    val loc: LiveData<Location>
        get() = _loc

    private val _dataHourlyDaily = MutableLiveData<Response<Data>>()
    val dataHourlyDaily:LiveData<Response<Data>>
        get() = _dataHourlyDaily

    private val _dataCurrent = MutableLiveData<Response<Current>>()
    val dataCurrent:LiveData<Response<Current>>
        get() = _dataCurrent

    init {
        getDeviceLocation()
    }

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

    fun setImage(imgView: ImageView, imgUrl: String) {
        imgView.load(imgUrl){
            placeholder(R.drawable.ic_error)
        }
    }

    fun icons() {
        icon["01d"] = "https://raw.githubusercontent.com/zeus2611/images/main/01d.png?token=AGZO2YPJCCMKHOZAPAJQXI3BFDCRM"
        icon["01n"] = "https://raw.githubusercontent.com/zeus2611/images/main/01n.png?token=AGZO2YJRYYMW2WLPNFXKUEDBFDC2G"
        icon["02d"] = "https://raw.githubusercontent.com/zeus2611/images/main/02d.png?token=AGZO2YIPBNFZJRRQJOE2IF3BFDDIW"
        icon["02n"] = "https://raw.githubusercontent.com/zeus2611/images/main/02n.png?token=AGZO2YIK3Q3LH4YRKUNZG53BFDDLM"
        icon["03d"] = "https://raw.githubusercontent.com/zeus2611/images/main/03d.png?token=AGZO2YNO7CLMZQSI36I6TATBFDDNY"
        icon["03n"] = "https://raw.githubusercontent.com/zeus2611/images/main/03d.png?token=AGZO2YNO7CLMZQSI36I6TATBFDDNY"
        icon["04d"] = "https://raw.githubusercontent.com/zeus2611/images/main/04d.png?token=AGZO2YOTMFKSYGGDG54L4ADBFDDPG"
        icon["04n"] = "https://raw.githubusercontent.com/zeus2611/images/main/04n.png?token=AGZO2YPC5STKGCA2D6SWGCDBFDDQI"
        icon["09d"] = "https://raw.githubusercontent.com/zeus2611/images/main/09d.png?token=AGZO2YOHWXYIAE4IZ4QBY73BFDDTI"
        icon["09n"] = "https://raw.githubusercontent.com/zeus2611/images/main/09d.png?token=AGZO2YOHWXYIAE4IZ4QBY73BFDDTI"
        icon["10d"] = "https://raw.githubusercontent.com/zeus2611/images/main/10d.png?token=AGZO2YJDXR5ZVXI44K2E4BLBFDDXW"
        icon["10n"] = "https://raw.githubusercontent.com/zeus2611/images/main/10n.png?token=AGZO2YJXRTMGR3YWKR6D63LBFDDYC"
        icon["11d"] = "https://raw.githubusercontent.com/zeus2611/images/main/11d.png?token=AGZO2YOZVPCKWYTEFDY5OE3BFDDZC"
        icon["11n"] = "https://raw.githubusercontent.com/zeus2611/images/main/11n.png?token=AGZO2YMEDO6Z26NUPZ5LTITBFDDZO"
        icon["13d"] = "https://raw.githubusercontent.com/zeus2611/images/main/13d.png?token=AGZO2YNXUATPBE7NEACLI5LBFDDVS"
        icon["13n"] = "https://raw.githubusercontent.com/zeus2611/images/main/13d.png?token=AGZO2YNXUATPBE7NEACLI5LBFDDVS"
        icon["50d"] = "https://raw.githubusercontent.com/zeus2611/images/main/50d.png?token=AGZO2YMY4WTSFL7UJIZ6PGLBFDD4I"
        icon["50n"] = "https://raw.githubusercontent.com/zeus2611/images/main/50n.png?token=AGZO2YP43CFDHPOWJZMYWTDBFDD4S"
    }

    fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(MainActivity()) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            Log.d(MainActivity.TAG, "Location found in View Model: " + lastKnownLocation!!.latitude.toString() + " " + lastKnownLocation!!.longitude.toString())
                            _loc.value = lastKnownLocation!!
                        }
                    } else {
                        Log.d(MainActivity.TAG, "Current location is null. Using defaults.")
                        Log.e(MainActivity.TAG, "Exception: %s", task.exception)
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

}