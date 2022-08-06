package com.breckneck.weatherappca

import com.breckneck.weatherappca.entity.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface WeatherStorageRemote {

    fun getWeather(city: String) : WeatherApiResponse

}