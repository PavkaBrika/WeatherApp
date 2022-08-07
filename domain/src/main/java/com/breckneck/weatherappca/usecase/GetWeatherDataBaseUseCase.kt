package com.breckneck.weatherappca.usecase

import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.repository.WeatherRepository

class GetWeatherDataBaseUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute() : WeatherDomain {
        return weatherRepository.getWeatherDatabase()
    }
}