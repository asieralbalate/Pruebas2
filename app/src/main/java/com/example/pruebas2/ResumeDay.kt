package com.example.pruebas2
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ResumeDay() {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Months header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Espacio vacío para la esquina superior izquierda
            Spacer(modifier = Modifier.width(10.dp))
            for (i in 1..monthsInYear) {
                Text(
                    text = i.toString(),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Calendar table
        repeat(daysInMonth) { dayIndex ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(monthsInYear) { columnIndex ->
                    val isWeekColumn = columnIndex == 0
                    val isDayColumn = columnIndex > 0


                    if (isWeekColumn) {
                        // Números de día en la primera columna
                        Text(
                            text = (dayIndex + 1).toString(),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }  else if (isDayColumn) {
                        // Celdas del calendario
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(Color.Green)
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ":)",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}




