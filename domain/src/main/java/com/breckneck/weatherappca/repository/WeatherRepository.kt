package com.breckneck.weatherappca.repository

import com.breckneck.weatherappca.model.WeatherDomain

interface WeatherRepository {

    fun getWeather(city: String) : WeatherDomain

}