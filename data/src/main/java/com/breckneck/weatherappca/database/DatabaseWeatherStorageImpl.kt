package com.breckneck.weatherappca.database

import android.content.Context
import androidx.room.Room
import com.breckneck.weatherappca.WeatherStorageDatabase
import com.breckneck.weatherappca.entity.Weather

private val SHARED_PREFS_NAME = "name"
private val WEATHER_ID = "id"

class DatabaseWeatherStorageImpl(context: Context) : WeatherStorageDatabase{

    val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    val db = Room.databaseBuilder(context, AppDataBase::class.java, "WeatherDatabase").build()

    override suspend fun saveWeather(weather: Weather) {
        var weatherId = sharedPrefs.getInt(WEATHER_ID, 0)
        weather.id =  weatherId
        db.appDao().insertWeather(weather = weather)
        weatherId++
        sharedPrefs.edit().putInt(WEATHER_ID, weatherId).apply()
    }

    override suspend fun getWeather(): Weather {
        return db.appDao().getWeather()
    }

}