package com.breckneck.weatherappca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.breckneck.weatherappca.entity.Weather

@Database(entities = [Weather::class], version = 4)
abstract class AppDataBase: RoomDatabase() {
    abstract fun appDao() : AppDao
}