package com.breckneck.weatherappca.database

import androidx.room.*
import com.breckneck.weatherappca.entity.Weather
import com.breckneck.weatherappca.entity.WeatherApiResponse


@Dao
interface AppDao {

    @Query("SELECT * FROM weather ORDER BY id DESC LIMIT 1")
    suspend fun getWeather(): Weather

    @Insert
    suspend fun insertWeather(weather: Weather)

    @Update
    suspend fun updateWeather(weather: Weather)

    @Delete
    suspend fun deleteWeather(weather: Weather)


}