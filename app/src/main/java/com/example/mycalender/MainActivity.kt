package com.example.mycalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycalender.model.*
import com.example.mycalender.ui.theme.MyCalenderTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
                        composable("calendar") {
                            CalendarScreen(
                                onLogoutClick = { navController.navigate("logout") },
                                onDateClick = { date -> navController.navigate("weather/$date") }
                            )
                        }
                        composable("logout") {
                            LogoutScreen(onLoginClick = { navController.navigate("calendar") })
                        }
                        composable("weather/{date}") { backStackEntry: NavBackStackEntry ->
                            val date = backStackEntry.arguments?.getString("date") ?: ""
                            WeatherScreen(date = date, city = "Mohali") {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarScreen(
    onLogoutClick: () -> Unit,
    onDateClick: (String) -> Unit
) {
    val months = remember { generateMonthsList() }
    val listState = rememberLazyListState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            state = listState
        ) {
            item { CustomToolbar(onLogoutClick = onLogoutClick) }

            itemsIndexed(
                items = months,
                key = { _, month -> month.monthName }
            ) { _, month ->
                MonthView(
                    month = month,
                    onDateClick = onDateClick
                )
            }
        }
    }
}

fun generateMonthsList(): List<MonthModel> {
    val list = mutableListOf<MonthModel>()
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    var date = LocalDate.now()
    val end = LocalDate.of(2025, 12, 31)

    while (!date.isAfter(end)) {
        val days = mutableListOf<String>()
        val headers = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        val firstDay = date.withDayOfMonth(1).dayOfWeek.value % 7
        repeat(firstDay) { days.add("") }

        val totalDays = date.lengthOfMonth()
        repeat(totalDays) { days.add((it + 1).toString()) }

        val remainder = days.size % 7
        if (remainder != 0) repeat(7 - remainder) { days.add("") }

        list.add(MonthModel(date.format(formatter), headers, days))
        date = date.plusMonths(1)
    }

    return list
}
