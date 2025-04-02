package com.example.mycalender.utils

import com.example.mycalender.data.model.MonthModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

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







@Composable
fun ReusableAlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}