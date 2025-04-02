package com.example.mycalender.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalender.data.model.MonthModel
import com.example.mycalender.utils.generateMonthsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {

    private val _months = MutableStateFlow<List<MonthModel>>(emptyList())
    val months: StateFlow<List<MonthModel>> = _months

    init {
        loadMonths()
    }

    private fun loadMonths() {
        viewModelScope.launch {
            _months.value = generateMonthsList()
        }
    }
}
