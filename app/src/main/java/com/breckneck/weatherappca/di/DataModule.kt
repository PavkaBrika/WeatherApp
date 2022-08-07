package com.breckneck.weatherappca.di

import com.breckneck.weatherappca.WeatherStorageDatabase
import com.breckneck.weatherappca.WeatherStorageRemote
import com.breckneck.weatherappca.database.DatabaseWeatherStorageImpl
import com.breckneck.weatherappca.remote.RemoteWeatherStorageImpl
import com.breckneck.weatherappca.remote.WeatherApi
import com.breckneck.weatherappca.repository.WeatherRepository
import com.breckneck.weatherappca.repository.WeatherRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<WeatherStorageDatabase> {
        DatabaseWeatherStorageImpl(context = get())
    }

    single<WeatherStorageRemote> {
        RemoteWeatherStorageImpl(weatherApi = get())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(weatherStorageRemote = get(), weatherStorageDatabase = get())
    }
}