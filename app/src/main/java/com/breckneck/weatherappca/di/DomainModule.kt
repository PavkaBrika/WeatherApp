package com.breckneck.weatherappca.di

import com.breckneck.weatherappca.usecase.GetWeatherDataBaseUseCase
import com.breckneck.weatherappca.usecase.GetWeatherUseCase
import com.breckneck.weatherappca.usecase.SaveWeatherUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetWeatherDataBaseUseCase> {
        GetWeatherDataBaseUseCase(weatherRepository = get())
    }

    factory<SaveWeatherUseCase> {
        SaveWeatherUseCase(weatherRepository = get())
    }

    factory<GetWeatherUseCase> {
        GetWeatherUseCase(weatherRepository = get())
    }
}