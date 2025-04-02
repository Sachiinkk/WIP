package com.example.mycalender.data.model
data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val country: String,
    val region: String,
    val localtime: String
)

data class Current(
    val temperature: Int,
    val humidity: Int,
    val wind_speed: Int,
    val weather_descriptions: List<String>
)
