package com.example.pruebas2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


// Function to show the resume of the day from the database
@Composable
fun ResumeDay(dataMap: MutableMap<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                                .background(getColorDay(dataMap, dayIndex, columnIndex)),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    }
                }
            }
        }
    }
}

// Function to show the resume of the food from the database
@Composable
fun ResumeFood(dataMap: MutableMap<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
// Function to show the resume of the sleep from the database
@Composable
fun ResumeSleep(dataMap: MutableMap<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                                .background(getColorSleep(dataMap, dayIndex, columnIndex))
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

// Function to show the resume of the spend from the database
@Composable
fun ResumeSpend(dataMap: MutableMap<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                                .background(getColorSpend(dataMap, dayIndex, columnIndex)),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    }
                }
            }
        }
    }
}
// Function to show the resume of the steps from the database
@Composable
fun ResumeSteps(dataMap: MutableMap<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
// Function to show the resume of the weather from the database
@Composable
fun ResumeWeather(dataMap: MutableMap<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
                                .background(getColorWeather(dataMap, dayIndex, columnIndex))
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
// Function to show the resume of the sports from the database
@Composable
fun ResumeWeights(dataMap: Map<String, IntArray>) {
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val calendar = java.util.Calendar.getInstance()
    calendar.timeInMillis = currentSelectedDateMillis
    val monthsInYear = 12
    val daysInMonth = 31
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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