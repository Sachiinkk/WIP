package com.example.mycalender.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mycalender.presentation.Component.CustomToolbar
import com.example.mycalender.data.model.MonthModel
import com.example.mycalender.utils.generateMonthsList
import kotlinx.coroutines.delay

@Composable
fun CalendarScreen(
    onDateClick: (String) -> Unit,
    onLogoutClick: () -> Unit
) {
    val months: List<MonthModel> = remember { generateMonthsList() }
    val listState = rememberLazyListState()

    var loading by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }

    // 🌐 Delayed navigation to WeatherScreen
    LaunchedEffect(selectedDate) {
        if (selectedDate.isNotEmpty()) {
            delay(1000)
            onDateClick(selectedDate)
            loading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 📅 Calendar UI (Blurred when loading)
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .then(if (loading) Modifier.blur(12.dp) else Modifier)
        ) {
            item {
                CustomToolbar(
                    userName = "Ram",
                    onLogoutClick = onLogoutClick
                )
            }

            itemsIndexed(items = months, key = { index, _ -> index }) { _, month ->
                MonthView(
                    month = month,
                    onDateClick = { date ->
                        selectedDate = date
                        loading = true
                    }
                )
            }
        }

        // ⏳ Loader Overlay
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}
