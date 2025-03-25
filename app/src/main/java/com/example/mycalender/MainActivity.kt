package com.example.mycalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycalender.model.CustomToolbar
import com.example.mycalender.model.MonthModel
import com.example.mycalender.model.MonthView
import com.example.mycalender.ui.theme.MyCalenderTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.mycalender.model.LogoutScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalenderTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF121212)) // Dark background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "calendar") {
                        composable("calendar") {
                            CalendarScreen(onLogoutClick = {
                                navController.navigate("logout")
                            })
                        }
                        composable("logout") {
                            LogoutScreen(onLoginClick = {
                                navController.navigate("calendar")
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarScreen(onLogoutClick: () -> Unit) {
    val months = remember { generateMonthsList() }
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item { CustomToolbar(onLogoutClick = onLogoutClick) }

        itemsIndexed(
            items = months,
            key = { _, month -> month.monthName }
        ) { _, month ->
            MonthView(month)
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
        if (remainder != 0) {
            repeat(7 - remainder) { days.add("") }
        }

        list.add(MonthModel(date.format(formatter), headers, days))
        date = date.plusMonths(1)
    }

    return list
}
