package com.example.mycalender.presentation.Weather
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalender.data.model.WeatherResponse
import com.example.mycalender.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<Result<WeatherResponse>?>(null)
    val weatherState: StateFlow<Result<WeatherResponse>?> = _weatherState

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            val result = getWeatherUseCase(city, apiKey)
            _weatherState.value = result
        }
    }
}
