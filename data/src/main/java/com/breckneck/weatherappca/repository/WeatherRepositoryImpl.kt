package com.breckneck.weatherappca.repository

import com.breckneck.weatherappca.WeatherStorageRemote
import com.breckneck.weatherappca.entity.WeatherApiResponse
import com.breckneck.weatherappca.model.WeatherDomain

class WeatherRepositoryImpl(private val weatherStorageRemote: WeatherStorageRemote) : WeatherRepository {


    override fun getWeather(city: String): WeatherDomain {
        val weather: WeatherApiResponse = weatherStorageRemote.getWeather(city = city)
        return WeatherDomain(city = weather.name, degrees = weather.main.temp, feelsLike = weather.main.feels_like, weather = weather.weather[0].description)
    }

}