package com.example.mycalender.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mycalender.model.HolidayData.holidayMap
import androidx.compose.ui.Alignment

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
                    if (day.isNotEmpty()) {
                        val isSunday = i == 0
                        val paddedDay = day.padStart(2, '0')
                        val monthNameShort = month.monthName.substringBefore(" ")
                        val holidayKey = "$paddedDay $monthNameShort"
                        val isHoliday = holidayMap.containsKey(holidayKey)

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isSunday || isHoliday) Color.Red else Color.White
                                )
                                .clickable {
                                    onDateClick("$paddedDay-$monthNameShort")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day,
                                color = if (isSunday || isHoliday) Color.White else Color.Black,
                                textAlign = TextAlign.Center
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
