package com.example.mycalender.data.remote
import com.example.mycalender.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current")
    suspend fun getWeather(
        @Query("access_key") apiKey: String,
        @Query("query") city: String
    ): WeatherResponse
}
