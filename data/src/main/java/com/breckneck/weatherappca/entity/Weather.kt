package com.breckneck.weatherappca.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey
    var id: Int,
    val city: String,
    val degrees: Double,
    val feelsLike: Double,
    val weather: String)