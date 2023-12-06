package com.example.pruebas2
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.sp

import com.example.pruebas2.ui.theme.*
import java.util.*

@Composable
fun ResumeFood(dataMap: Map<String, IntArray>) {
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
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Espacio vacío para la esquina superior izquierda
            Spacer(modifier = Modifier.weight(1f))
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(monthsInYear + 1) { columnIndex ->
                    val isWeekColumn = columnIndex == 0
                    val isDayColumn = columnIndex > 0

                    if (isWeekColumn) {
                        // Números de día en la primera columna
                        Text(
                            text = (dayIndex + 1).toString(),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }  else if (isDayColumn) {
                        // Celdas del calendario
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(getColorFood(dataMap, dayIndex, columnIndex))
                            ,
                            contentAlignment = Alignment.Center
                        ) {

                        }
                    }
                }
            }
        }
    }
}


fun getColorFood(dataMap: Map<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1 && dayCol == col) {
            value = m.value.get(5)
            break
        }
    }
    return when {
        value == 0 -> FoodColor0
        value == 1 -> FoodColor1
        value == 2 -> FoodColor2
        value == 3 -> FoodColor3
        value == 4 -> FoodColor4
        value == 5 -> FoodColor5
        value == 6 -> FoodColor6
        value == 7 -> FoodColor7
        value == 8 -> FoodColor8
        value == 9 -> FoodColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}
