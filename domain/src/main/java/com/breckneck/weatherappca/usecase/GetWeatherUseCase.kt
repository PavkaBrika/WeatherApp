package com.breckneck.weatherappca.usecase

import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    fun execute(city: String): WeatherDomain {
        return weatherRepository.getWeather(city = city)
    }

}