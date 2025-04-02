package com.example.mycalender.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mycalender.data.model.MonthModel
import com.example.mycalender.presentation.Utils.Holiday.holidays

@Composable
fun MonthView(
    month: MonthModel,
    onDateClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {

        // Month Title
        Text(
            text = month.monthName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )

        // Week Headers
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

        // Days Grid
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
                    val isHoliday = holidays.containsKey(holidayKey)

                    if (day.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(40.dp)
                                .background(
                                    color = if (isSunday || isHoliday) Color.Red else Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    onDateClick("$paddedDay-$monthNameShort")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day,
                                color = if (isSunday || isHoliday) Color.White else Color.Black
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(40.dp).padding(4.dp))
                    }
                }
            }
        }
    }
}
