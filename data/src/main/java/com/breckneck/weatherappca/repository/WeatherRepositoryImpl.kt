package com.breckneck.weatherappca.repository

import com.breckneck.weatherappca.WeatherStorageDatabase
import com.breckneck.weatherappca.WeatherStorageRemote
import com.breckneck.weatherappca.entity.ExtraWeather
import com.breckneck.weatherappca.entity.Main
import com.breckneck.weatherappca.entity.Weather
import com.breckneck.weatherappca.entity.WeatherApiResponse
import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.util.Resource

class WeatherRepositoryImpl(private val weatherStorageRemote: WeatherStorageRemote, private val weatherStorageDatabase: WeatherStorageDatabase) : WeatherRepository {


    override suspend fun getWeather(city: String): Resource<WeatherDomain>  {

        return try {
            val weather = weatherStorageRemote.getWeather(city = city)
            Resource.Success(
                data = WeatherDomain(city = weather.body()!!.name, degrees = weather.body()!!.main.temp, feelsLike = weather.body()!!.main.feels_like, weather = weather.body()!!.weather[0].description)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("Wrong city")
        }
    }

    override suspend fun saveWeather(weather: WeatherDomain) {
        weatherStorageDatabase.saveWeather(Weather(city = weather.city, degrees = weather.degrees, feelsLike = weather.feelsLike, weather = weather.weather, id = 0))
    }

    override suspend fun getWeatherDatabase(): WeatherDomain {
        val weather = weatherStorageDatabase.getWeather()
        return WeatherDomain(city = weather.city, degrees = weather.degrees, feelsLike = weather.feelsLike, weather = weather.weather)
    }

}