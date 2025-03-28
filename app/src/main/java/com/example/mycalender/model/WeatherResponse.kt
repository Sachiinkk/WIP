package com.example.mycalender.model

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val country: String
)

data class Current(
    val temperature: Int,
    val weather_descriptions: List<String>,
    val humidity: Int,
    val wind_speed: Int
)
