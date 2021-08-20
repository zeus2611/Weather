package com.nischay.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nischay.weather.data.Repository

class MainViewModelFactory(
    private val repository: Repository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}