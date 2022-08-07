package com.breckneck.weatherappca.repository

import com.breckneck.weatherappca.WeatherStorageDatabase
import com.breckneck.weatherappca.WeatherStorageRemote
import com.breckneck.weatherappca.entity.ExtraWeather
import com.breckneck.weatherappca.entity.Main
import com.breckneck.weatherappca.entity.WeatherApiResponse
import com.breckneck.weatherappca.model.WeatherDomain

class WeatherRepositoryImpl(private val weatherStorageRemote: WeatherStorageRemote, private val weatherStorageDatabase: WeatherStorageDatabase) : WeatherRepository {


    override suspend fun getWeather(city: String): WeatherDomain {
        val weather: WeatherApiResponse = weatherStorageRemote.getWeather(city = city)
        return WeatherDomain(city = weather.name, degrees = weather.main.temp, feelsLike = weather.main.feels_like, weather = weather.weather[0].description)
    }

    override suspend fun saveWeather(weather: WeatherDomain) {
        weatherStorageDatabase.saveWeather(WeatherApiResponse(name = weather.city, main = Main(temp = weather.degrees, feels_like = weather.feelsLike), weather = listOf(ExtraWeather(description = weather.weather))))
    }

}