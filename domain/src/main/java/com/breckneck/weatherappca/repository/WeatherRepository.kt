package com.breckneck.weatherappca.repository

import com.breckneck.weatherappca.model.WeatherDomain

interface WeatherRepository {

    suspend fun getWeather(city: String) : WeatherDomain

    suspend fun saveWeather(weather: WeatherDomain)
}