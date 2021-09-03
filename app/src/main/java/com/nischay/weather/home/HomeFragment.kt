package com.nischay.weather.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nischay.weather.MainViewModel
import com.nischay.weather.MainViewModelFactory
import com.nischay.weather.R
import com.nischay.weather.data.Repository
import com.nischay.weather.databinding.FragmentHomeBinding
import com.nischay.weather.util.Constants.Companion.icon
import com.nischay.weather.util.Utils.getDate
import com.nischay.weather.util.Utils.getDay
import com.nischay.weather.util.Utils.getTime
import com.nischay.weather.util.Utils.hPaToBar
import com.nischay.weather.util.Utils.mToKm
import com.nischay.weather.util.Utils.tempKtoC
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import kotlin.collections.HashMap

class HomeFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if(hasLocationPermission()){
            getLocation()
            Log.d("HomeFragment", "Get Location")
        } else {
            requestLocationPermission()
        }

        viewModel.icons()

        binding.pullToRefresh.setOnRefreshListener {
            getLocation()

            binding.pullToRefresh.isRefreshing = false
        }


        viewModel.dataCurrent.observe(viewLifecycleOwner, { response->
            Log.d("MainActivity", "Data Change Observed")
            binding.apply{
                tvTemp.text = response.body()?.main?.temp?.let { tempKtoC(it).toString() }
                tvCity.text = response.body()?.name + ", " + response.body()?.sys?.country
                condition.text = response.body()?.weat?.get(0)?.main
                val icon = icon[response.body()?.weat?.get(0)?.icon]
                if (icon != null) {
                    weather.setImageResource(icon)
                }

                val sunrise = response.body()?.sys?.sunrise?.times(1000)
                val sunset = response.body()?.sys?.sunset?.times(1000)

                dateTime.text = sunrise?.let { getDay(it) } + ", " + sunrise?.let { getDate(it) }
                tempFeels.text = response.body()?.main?.feels_like?.let { tempKtoC(it).toString() }
                sunriseTime.text = sunrise?.let { getTime(it) }
                sunsetTime.text = sunset?.let { getTime(it) }

                visibilityValue.text = response.body()?.visibility?.let { mToKm(it) }.toString()
                humidityValue.text = response.body()?.main?.humidity.toString() + "%"
                pressureValue.text = response.body()?.main?.pressure?.let { hPaToBar(it) }.toString()
                windValue.text = response.body()?.wind?.speed.toString()
            }
        })

        viewModel.dataHourlyDaily.observe(viewLifecycleOwner, {
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        try {
            if (hasLocationPermission()) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    val options: HashMap<String, String> = HashMap()
                    options["lat"] = location.latitude.toString()
                    options["lon"] = location.longitude.toString()
                    options["appid"] = "e7b2fe1c787be0717df45d7576b2b86e"

                    viewModel.getDataCurrent(options)
                    viewModel.getDataHourlyDaily(options)
                }
            }
        } catch (e: Exception) {
            Log.e("Exception: ${e.message}", e.message.toString())
        }
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "Location is needed to show relevant data.",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms.first())) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted",
            Toast.LENGTH_SHORT
        ).show()

        getLocation()
    }

}