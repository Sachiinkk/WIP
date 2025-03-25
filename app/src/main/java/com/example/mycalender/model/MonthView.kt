package com.example.mycalender.model

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mycalender.model.HolidayData.holidayMap

@Composable
fun MonthView(month: MonthModel) {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(8.dp)) {

        // Month Title
        Text(
            text = month.monthName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        // Day Headers
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            month.dayHeaders.forEach {
                Text(
                    text = it,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }


        // Days
        val chunked = month.days.chunked(7)
        chunked.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEachIndexed { i, day ->
                    val isSunday = i == 0
                    val paddedDay = day.padStart(2, '0')
                    val monthNameShort = month.monthName.substringBefore(" ")
                    val holidayKey = "$paddedDay $monthNameShort"
                    val isHoliday = holidayMap.containsKey(holidayKey)

                    DayItem(day = day, isHoliday = isHoliday, isSunday = isSunday) {
                        if (isHoliday) {
                            Toast.makeText(context, holidayMap[holidayKey], Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
