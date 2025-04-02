package com.example.mycalender.domain.usecase

import com.example.mycalender.data.model.WeatherResponse
import com.example.mycalender.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(city: String, apiKey: String): Result<WeatherResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val data = repository.getWeather(city, apiKey)
                Result.success(data)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}
