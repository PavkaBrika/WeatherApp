package com.breckneck.weatherappca

import com.breckneck.weatherappca.entity.Weather
import com.breckneck.weatherappca.entity.WeatherApiResponse

interface WeatherStorageDatabase {

    suspend fun saveWeather(weather: Weather)

    suspend fun getWeather() : Weather

    suspend fun updateWeather(weather: Weather)
}