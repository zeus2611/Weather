package com.nischay.weather.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nischay.weather.MainViewModel
import com.nischay.weather.MainViewModelFactory
import com.nischay.weather.R
import com.nischay.weather.data.Repository
import com.nischay.weather.databinding.FragmentHomeBinding
import com.nischay.weather.util.Utils.tempKtoC

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getDeviceLocation()
        viewModel.icons()

        val options: HashMap<String, String> = HashMap()
        options["lat"] = "23.3751579"
        options["lon"] = "85.4290136"
        options["appid"] = "e7b2fe1c787be0717df45d7576b2b86e"

        viewModel.getDataCurrent(options)

        viewModel.dataCurrent.observe(viewLifecycleOwner, { response->
            Log.d("MainActivity", "Data Change Observed")
            binding.tvTemp.text = response.body()?.main?.temp?.let { tempKtoC(it).toString() }
            binding.tvCity.text = response.body()?.name
            binding.condition.text = response.body()?.weat?.get(0)?.main
            val icon = viewModel.icon[response.body()?.weat?.get(0)?.icon]
            if (icon != null) {
                viewModel.setImage(binding.weather, icon)
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}