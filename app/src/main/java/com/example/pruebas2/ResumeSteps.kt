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
import com.example.pruebas2.ui.theme.StepsColor0
import com.example.pruebas2.ui.theme.StepsColor1
import com.example.pruebas2.ui.theme.StepsColor2
import com.example.pruebas2.ui.theme.StepsColor3
import com.example.pruebas2.ui.theme.StepsColor4
import com.example.pruebas2.ui.theme.StepsColor5
import com.example.pruebas2.ui.theme.StepsColor6
import com.example.pruebas2.ui.theme.StepsColor7
import com.example.pruebas2.ui.theme.StepsColor8
import com.example.pruebas2.ui.theme.StepsColor9
import java.util.*

@Composable
fun ResumeSteps(dataMap: Map<String, IntArray>) {
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

                        Text(
                            text = (dayIndex + 1).toString(),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }  else if (isDayColumn) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(getColorSteps(dataMap, dayIndex, columnIndex))
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


fun getColorSteps(dataMap: Map<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[2]
            break
        }
    }
    return when {
        value == 0 -> StepsColor0
        value == 1 -> StepsColor1
        value == 2 -> StepsColor2
        value == 3 -> StepsColor3
        value == 4 -> StepsColor4
        value == 5 -> StepsColor5
        value == 6 -> StepsColor6
        value == 7 -> StepsColor7
        value == 8 -> StepsColor8
        value == 9 -> StepsColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}
