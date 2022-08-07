package com.breckneck.weatherappca.remote

import com.breckneck.weatherappca.entity.WeatherApiResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers

import retrofit2.http.Query

interface WeatherApi {

    @GET("./weather?appid=dbb8b98aa3c25c1fd91e92b660a26c5d&units=metric&lang=ru")
    @Headers("Content-Type: application/json")
    suspend fun getWeather(@Query("q") city: String) : Response<WeatherApiResponse>

}