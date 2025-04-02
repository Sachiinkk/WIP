package com.example.mycalender.data.repository

import com.example.mycalender.data.model.WeatherResponse
import com.example.mycalender.data.remote.WeatherApiService
import com.example.mycalender.data.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(city: String, apiKey: String): WeatherResponse {
        return apiService.getWeather(apiKey, city) // ✅ Order matches interface
    }
}
