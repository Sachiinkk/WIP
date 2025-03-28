package com.example.mycalender.model

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(date: String, city: String, onBack: () -> Unit) {
    val context = LocalContext.current
    var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.getWeather(
                apiKey = "348a419a9d1571fe76001980d4fa77a2",
                city = city
            )
            weatherData = response
        } catch (e: Exception) {
            Toast.makeText(context, "❌ ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📅 $date", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))

        if (weatherData != null) {
            Text("🌡️ Temp: ${weatherData!!.current.temperature}°C", fontSize = 20.sp, color = Color.White)
            Text("💧 Humidity: ${weatherData!!.current.humidity}%", fontSize = 20.sp, color = Color.White)
            Text("🌬️ Wind: ${weatherData!!.current.wind_speed} km/h", fontSize = 20.sp, color = Color.White)
            Text("🌥️ ${weatherData!!.current.weather_descriptions.firstOrNull()}", fontSize = 20.sp, color = Color.White)
        } else {
            CircularProgressIndicator(color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("🔙 Back")
        }
    }
}
