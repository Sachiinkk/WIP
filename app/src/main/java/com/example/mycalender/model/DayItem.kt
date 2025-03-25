package com.example.mycalender.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip

@Composable
fun DayItem(day: String, isHoliday: Boolean, isSunday: Boolean, onClick: () -> Unit) {
    if (day.isNotEmpty()) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day,
                color = when {
                    isHoliday || isSunday -> Color.Red
                    else -> Color.Black
                }
            )
        }
    } else {
        Spacer(modifier = Modifier
            .padding(4.dp)
            .size(40.dp))
    }
}


