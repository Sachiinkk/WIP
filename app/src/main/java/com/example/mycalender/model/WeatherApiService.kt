package com.example.mycalender.model

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current")
    suspend fun getWeather(
        @Query("access_key") apiKey: String,
        @Query("query") city: String
    ): WeatherResponse
}
