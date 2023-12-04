package com.example.pruebas2
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.DiaryColor0
import com.example.pruebas2.ui.theme.DiaryColor1
import com.example.pruebas2.ui.theme.DiaryColor2
import com.example.pruebas2.ui.theme.DiaryColor3
import com.example.pruebas2.ui.theme.DiaryColor4
import com.example.pruebas2.ui.theme.DiaryColor5
import com.example.pruebas2.ui.theme.DiaryColor6
import com.example.pruebas2.ui.theme.DiaryColor7
import com.example.pruebas2.ui.theme.DiaryColor8
import com.example.pruebas2.ui.theme.DiaryColor9
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ResumeDay() {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31

    val dataMap: Map<String, Int> = mapOf(
        "11-02-23" to 0,
        "12-03-23" to 1,
        "10-01-23" to 2,
        "20-01-23" to 3,
        "15-06-23" to 4,
        "15-08-23" to 5,
        "10-08-23" to 6,
        "12-12-23" to 7,
    )

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Months header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
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
                            fontWeight = FontWeight.Bold
                        )
                    }  else if (isDayColumn) {
                        // Celdas del calendario
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(getColor(dataMap, dayIndex, columnIndex))
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


fun getColor(dataMap: Map<String, Int>, dayRow: Int, dayCol: Int ): Color{
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
        value == 0 -> DiaryColor0
        value == 1 -> DiaryColor1
        value == 2 -> DiaryColor2
        value == 3 -> DiaryColor3
        value == 4 -> DiaryColor4
        value == 5 -> DiaryColor5
        value == 6 -> DiaryColor6
        value == 7 -> DiaryColor7
        value == 8 -> DiaryColor8
        value == 9 -> DiaryColor9
        else -> {Color.Gray}
    }
}
