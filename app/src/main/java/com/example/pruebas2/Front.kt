package com.example.pruebas2


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Front(navController: NavHostController) {
    Surface(Modifier.fillMaxSize().clickable { navController.navigate("Calendar") }) {
        val currentDate = getCurrentDate()
        Text(
            text = currentDate,
            modifier = Modifier.padding(16.dp),
        )
    }
}

private fun getCurrentDate(): String {
    val calendar = java.util.Calendar.getInstance()
    val formatter = SimpleDateFormat("d", Locale.getDefault())
    val dayOfMonth = formatter.format(calendar.time).toInt()
    val daySuffix = getDayOfMonthSuffix(dayOfMonth)
    val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    return "${dayOfMonth}$daySuffix of ${dateFormat.format(calendar.time)}"
}

private fun getDayOfMonthSuffix(n: Int): String {
    return when {
        n in 11..13 -> "th"
        n % 10 == 1 -> "st"
        n % 10 == 2 -> "nd"
        n % 10 == 3 -> "rd"
        else -> "th"
    }
}
