package com.breckneck.weatherappca.usecase

import com.breckneck.weatherappca.model.WeatherDomain
import com.breckneck.weatherappca.repository.WeatherRepository
import com.breckneck.weatherappca.util.Resource

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(city: String): Resource<WeatherDomain> {
        return weatherRepository.getWeather(city = city)
    }

}