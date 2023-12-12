package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

// Format text into the format selected
@SuppressLint("SimpleDateFormat")
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}

// Format text into date
@SuppressLint("SimpleDateFormat")
fun newFormatDateForDisplay(millis: Long): String {
    val date = Date(millis)
    val dayOfMonth = SimpleDateFormat("dd").format(date)
    val month = SimpleDateFormat("MM").format(date)
    val year = SimpleDateFormat("yyyy").format(date)
    return "$dayOfMonth-$month-$year"
}

// Format the date into text
@Composable
fun formatDate(date: LocalDate): String {
    val dayOfMonth = date.dayOfMonth
    val month = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = date.year

    val dayWithExtension = when {
        dayOfMonth in 11..13 -> "${dayOfMonth}th"
        dayOfMonth % 10 == 1 -> "${dayOfMonth}st"
        dayOfMonth % 10 == 2 -> "${dayOfMonth}nd"
        dayOfMonth % 10 == 3 -> "${dayOfMonth}rd"
        else -> "${dayOfMonth}th"
    }
    return "$dayWithExtension of $month $year"
}
