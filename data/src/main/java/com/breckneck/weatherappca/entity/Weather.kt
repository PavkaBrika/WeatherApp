package com.breckneck.weatherappca.entity

import androidx.room.Entity

@Entity
data class WeatherApiResponse(
    val name: String,
    val main: Main,
    val weather: List<ExtraWeather>
)

data class Main(
    val temp: Double,
    val feels_like: Double
)

data class ExtraWeather(
    val description: String
)
