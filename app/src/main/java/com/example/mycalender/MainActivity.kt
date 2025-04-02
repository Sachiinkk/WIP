package com.example.mycalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.mycalender.presentation.Component.LogoutScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycalender.presentation.weather.WeatherScreen
import com.example.mycalender.ui.theme.MyCalenderTheme
import com.example.mycalender.presentation.calendar.CalendarScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalenderTheme {
                val navController = rememberNavController()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF121212))
                ) {
                    NavHost(navController = navController, startDestination = "calendar") {
                        addCalendarRoute(navController)
                        addWeatherRoute(navController)
                        addLogoutRoute(navController)
                    }
                }
            }
        }
    }
}

// ---------- Reusable Navigation Routes ----------

private fun NavGraphBuilder.addCalendarRoute(navController: NavHostController) {
    composable("calendar") {
        CalendarScreen(
            onDateClick = { date ->navController.navigateToWeather(date)},
            onLogoutClick = { navController.navigate("logout") }
        )
    }
}

private fun NavGraphBuilder.addWeatherRoute(navController: NavHostController) {
    composable(
        route = "weather/{date}",
        arguments = listOf(navArgument("date") { type = NavType.StringType })
    ) { backStackEntry ->
        val date = backStackEntry.arguments?.getString("date") ?: ""
        WeatherScreen(
            date = date,
            city = "Mohali",
            onBack = { navController.popBackStack() }
        )
    }
}

// ---------- Navigation Helper ----------

private fun NavHostController.navigateToWeather(date: String) {
    this.navigate("weather/$date")
}


//-----------------Logout Navigate------------------

private fun NavGraphBuilder.addLogoutRoute(navController: NavHostController) {
    composable("logout") {
        LogoutScreen(
            onBack = { navController.navigate("calendar") }
        )
    }
}
