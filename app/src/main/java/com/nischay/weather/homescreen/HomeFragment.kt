package com.nischay.weather.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.nischay.weather.MainActivity
import com.nischay.weather.MainViewModel
import com.nischay.weather.R
import com.nischay.weather.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel.loc.observe(viewLifecycleOwner, {
            binding.text.text = it.toString()
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}