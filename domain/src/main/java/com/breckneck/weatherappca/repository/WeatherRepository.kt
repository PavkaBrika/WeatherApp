package com.breckneck.weatherappca.repository

import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.util.Resource

interface WeatherRepository {

    suspend fun getWeather(city: String) : Resource<WeatherDomain>

    suspend fun saveWeather(weather: WeatherDomain)

    suspend fun getWeatherDatabase() : WeatherDomain

    suspend fun updateWeather(weather: WeatherDomain)
}