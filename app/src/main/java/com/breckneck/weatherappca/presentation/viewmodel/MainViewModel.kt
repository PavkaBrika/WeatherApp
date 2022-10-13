package com.breckneck.weatherappca.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.usecase.GetWeatherDataBaseUseCase
import com.breckneck.weatherappca.usecase.GetWeatherUseCase
import com.breckneck.weatherappca.usecase.SaveWeatherUseCase
import com.breckneck.weatherappca.usecase.UpdateWeatherUseCase
import com.breckneck.weatherappca.util.Resource
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherDataBaseUseCase: GetWeatherDataBaseUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    private val updateWeatherUseCase: UpdateWeatherUseCase
): ViewModel() {

    var resultWeatherRemote = MutableLiveData<Resource<WeatherDomain>>()
    var resultWeatherDatabase = MutableLiveData<WeatherDomain>()
    var isOpened = true

    init {
        Log.e("TAG", "VM Created")
    }

    fun getWeatherRemote(city: String) {
        viewModelScope.launch {
            val weatherRemote = getWeatherUseCase.execute(city = city)
            resultWeatherRemote.value = weatherRemote
            Log.e("TAG", "Weather GOT FROM REMOTE")
        }
    }

    fun getWeatherDatabase() : LiveData<WeatherDomain> {
        var weatherDatabase: WeatherDomain
        viewModelScope.launch {
            if (isOpened) {
                weatherDatabase = getWeatherDataBaseUseCase.execute()
//                resultWeatherDatabase.value = weatherDatabase
                Log.e("TAG", "Weather GOT FROM DATABASE")
                isOpened = false
            }
        }
        return weatherDatabase
    }

    fun saveWeather(weather: WeatherDomain) {
        viewModelScope.launch {
            val weather = saveWeatherUseCase.execute(weather = weather)
            Log.e("TAG", "Weather SAVED")
        }
    }

    fun updateWeather(weather: WeatherDomain) {
        viewModelScope.launch {
            val weather = updateWeatherUseCase.execute(weather = weather)
            Log.e("TAG", "Weather UPDATED")
        }
    }

    override fun onCleared() {
        Log.e("TAG", "VM CLEARED")
        super.onCleared()
    }

}