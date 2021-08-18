package com.nischay.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MainViewModel: ViewModel() {
    val loc: MutableLiveData<LatLng> by lazy {
        MutableLiveData<LatLng>()
    }
}