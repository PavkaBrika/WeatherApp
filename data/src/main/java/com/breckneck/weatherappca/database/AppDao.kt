package com.breckneck.weatherappca.database

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface AppDao {

    @Insert
    suspend fun insertWeather(weather)
}