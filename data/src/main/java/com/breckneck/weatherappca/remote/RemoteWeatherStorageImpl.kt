package com.breckneck.weatherappca.remote

import com.breckneck.weatherappca.WeatherStorageRemote
import com.breckneck.weatherappca.entity.WeatherApiResponse
import retrofit2.Response

class RemoteWeatherStorageImpl(private val weatherApi: WeatherApi) : WeatherStorageRemote {


    override suspend fun getWeather(city: String): Response<WeatherApiResponse> {
        return weatherApi.getWeather(city = city)
    }


}