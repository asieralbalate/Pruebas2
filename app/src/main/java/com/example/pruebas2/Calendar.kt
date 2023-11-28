package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Calendar(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        DatePickerView(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerView(navController: NavHostController) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val datePickerState =
        rememberDatePickerState(currentSelectedDateMillis, selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                // Permitir la selección de todas las fechas
                return true
            }
        })

    val formattedDate = datePickerState.selectedDateMillis?.let {
        formatDateForDisplay(it)
    }
    val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        DatePicker(dateFormatter =  DatePickerDefaults.dateFormatter() ,
            headline = {
                if (selectedDate == null) {
                    Text(text = "No date selected")
                } else {
                    Text(text = selectedDate.toString())
                }
            },
            title = { Text(text = "Calendairo") },
            state = datePickerState,
            showModeToggle = true
        )
        Spacer(
            modifier = Modifier.height(
                32.dp
            )
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate("Diary/${formattedDate}") }, content = { Text(text = "Diary") })
            Button(onClick = { navController.navigate("Resume") }, content = { Text(text = "Resume") })
        }
    }
}


@SuppressLint("SimpleDateFormat")
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}

@SuppressLint("SimpleDateFormat")
fun formatDateForDisplay(millis: Long): String {
    val date = Date(millis)
    val dayOfMonth = SimpleDateFormat("dd").format(date)
    val month = SimpleDateFormat("MMMM").format(date)
    val year = SimpleDateFormat("yyyy").format(date)

    val daySuffix = when (dayOfMonth.toInt()) {
        in 11..13 -> "th"
        else -> when (dayOfMonth.toInt() % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

    return "$dayOfMonth$daySuffix of $month $year"
}