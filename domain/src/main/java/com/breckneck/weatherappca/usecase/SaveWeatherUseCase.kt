package com.breckneck.weatherappca.usecase

import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.repository.WeatherRepository

class SaveWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(weather: WeatherDomain) {
        weatherRepository.saveWeather(weather = weather)
    }

}