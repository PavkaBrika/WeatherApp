package com.breckneck.weatherappca

import com.breckneck.weatherappca.entity.WeatherApiResponse

interface WeatherStorageRemote {

    suspend fun getWeather(city: String) : WeatherApiResponse

}