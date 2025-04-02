package com.example.mycalender.data.repository

import com.example.mycalender.data.model.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse
}
