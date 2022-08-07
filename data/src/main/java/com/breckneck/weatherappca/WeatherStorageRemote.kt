package com.breckneck.weatherappca

import com.breckneck.weatherappca.entity.WeatherApiResponse
import retrofit2.Response

interface WeatherStorageRemote {

    suspend fun getWeather(city: String) : Response<WeatherApiResponse>

}