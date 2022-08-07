package com.breckneck.weatherappca

import com.breckneck.weatherappca.entity.WeatherApiResponse

interface WeatherStorageDatabase {

    suspend fun saveWeather(weather: WeatherApiResponse)

}