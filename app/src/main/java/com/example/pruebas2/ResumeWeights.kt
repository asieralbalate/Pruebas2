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
fun ResumeWeights(dataMap: Map<String, Int>) {
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
                                .background(getColorWeights(dataMap, dayIndex, columnIndex))
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


fun getColorWeights(dataMap: Map<String, Int>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value
            break
        }
    }
    return when {
        value == 0 -> WeightsColor0
        value == 1 -> WeightsColor1
        value == 2 -> WeightsColor2
        value == 3 -> WeightsColor3
        value == 4 -> WeightsColor4
        value == 5 -> WeightsColor5
        value == 6 -> WeightsColor6
        value == 7 -> WeightsColor7
        value == 8 -> WeightsColor8
        value == 9 -> WeightsColor9
        else -> {Color.Gray}
    }
}
