package com.example.mycalender.presentation.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycalender.R
import com.example.mycalender.data.model.WeatherResponse
import com.example.mycalender.domain.usecase.GetWeatherUseCase
import com.example.mycalender.utils.ReusableAlertDialog
import kotlinx.coroutines.launch
import com.example.mycalender.data.repository.WeatherRepositoryImpl
import com.example.mycalender.data.remote.RetrofitInstance
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeatherScreen(
    date: String,
    city: String,
    onBack: () -> Unit,
    getWeatherUseCase: GetWeatherUseCase = GetWeatherUseCase(
        WeatherRepositoryImpl(apiService = RetrofitInstance.api)
    )
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF4F93F0), Color(0xFF001F54))
    )

    var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            val result = getWeatherUseCase(city, "348a419a9d1571fe76001980d4fa77a2")
            if (result.isSuccess) {
                weatherData = result.getOrNull()
            } else {
                errorMessage = "❌ ${result.exceptionOrNull()?.message ?: "Something went wrong"}"
                showDialog = true
            }
        }
    }

    if (showDialog) {
        ReusableAlertDialog(
            title = "Error",
            message = errorMessage,
            onDismiss = { showDialog = false },
            onConfirm = { showDialog = false }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (weatherData == null) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("WEEK WEATHER FORECAST", fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                TodayForecast(weatherData!!)
                Spacer(modifier = Modifier.height(24.dp))
                WeekForecast(weatherData!!)

                Spacer(modifier = Modifier.height(24.dp))
                androidx.compose.material3.Button(onClick = onBack) {
                    Text("🔙 Back")
                }
            }
        }
    }
}

@Composable
fun TodayForecast(weather: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text("${LocalDate.now()}", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            WeatherCard(
                time = "Now",
                icon = R.drawable.sun,
                temp = "${weather.current.temperature}°C",
                humidity = "${weather.current.humidity}%",
                wind = "${weather.current.wind_speed} KM/H"
            )
        }
    }
}

@Composable
fun WeekForecast(weather: WeatherResponse) {
    val today = LocalDate.now()
    val temps = List(7) { "${weather.current.temperature}°C" }
    val icons = listOf(
        R.drawable.sun, R.drawable.sun, R.drawable.moon, R.drawable.rain,
        R.drawable.rain, R.drawable.moon, R.drawable.sun
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (i in 0 until 7) {
            val futureDate = today.plusDays(i.toLong())
            val day = futureDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase()
            val dateNum = futureDate.dayOfMonth.toString()

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(day, color = Color.White, fontSize = 14.sp)
                Text(dateNum, color = Color.White, fontWeight = FontWeight.Bold)
                Image(painter = painterResource(id = icons[i]), contentDescription = null, modifier = Modifier.size(32.dp))
                Text(temps[i], color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun WeatherCard(time: String, icon: Int, temp: String, humidity: String, wind: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(time, color = Color.White, fontSize = 14.sp)
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Text(temp, color = Color.White, fontWeight = FontWeight.Bold)
        Text("Humidity $humidity", color = Color.White, fontSize = 10.sp)
        Text("Wind $wind", color = Color.White, fontSize = 10.sp)
    }
}

