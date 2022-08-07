package com.breckneck.weatherappca.di

import com.breckneck.weatherappca.usecase.GetWeatherUseCase
import org.koin.dsl.module

val domainModule = module {



    factory<GetWeatherUseCase> {
        GetWeatherUseCase(weatherRepository = get())
    }
}