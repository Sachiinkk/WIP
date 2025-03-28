package com.example.mycalender.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.launch
import com.example.mycalender.model.QuoteUtils.getQuoteForDate

@Composable
fun DayItem(
    day: String,
    isHoliday: Boolean,
    isSunday: Boolean,
    isSaturday : Boolean,
    monthName: String,
    snackbarHostState: SnackbarHostState,
    onClick : ()-> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val quote = getQuoteForDate(day, monthName.take(3), isSunday, isSaturday,isHoliday)

    if (day.isNotEmpty()) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .clickable {
//                    coroutineScope.launch {
//                        snackbarHostState.currentSnackbarData?.dismiss()
//                        snackbarHostState.showSnackbar(quote)
//                    }
                    if(day.isNotEmpty()){
                        onClick()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day,
                color = if (isSunday || isHoliday) Color.Red else Color.Black
            )
        }
    } else {
        Spacer(modifier = Modifier.padding(4.dp).size(40.dp))
    }
}
