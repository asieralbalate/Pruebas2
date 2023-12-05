package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.BoxColor
import com.example.pruebas2.ui.theme.FontTittle
import com.example.pruebas2.ui.theme.RegText
import com.example.pruebas2.ui.theme.TopBarColor
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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
        rememberDatePickerState(
            currentSelectedDateMillis,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    // Permitir la selección de todas las fechas
                    return true
                }
            })

    val newFormattedDate = datePickerState.selectedDateMillis?.let {
        newFormatDateForDisplay(it)
    }
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }

    val selectedYear = newFormattedDate?.split("-")?.get(2)
    Column(
        modifier = Modifier.background(BoxColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DatePicker(
            colors = DatePickerDefaults.colors(BoxColor),
            dateFormatter = DatePickerDefaults.dateFormatter(),
            headline = {
                if (selectedDate == null) {
                    Text(text = "No date selected", modifier = Modifier.padding(start = 10.dp))
                } else {
                    Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = selectedDate.toString(),
                            modifier = Modifier.padding(start = 20.dp, top = 4.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = RegText
                        )

                    }
                }
            },
            title = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(TopBarColor)
                        .height(58.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calright),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "CalenDiary",
                        color = Color.White,
                        fontSize = 60.sp,
                        fontFamily = FontTittle
                    )
                    Image(
                        painter = painterResource(id = R.drawable.calleft),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 10.dp)
                    )
                }


            },
            state = datePickerState,
            showModeToggle = true
        )
        Spacer(
            modifier = Modifier.height(
                12.dp
            )
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { navController.navigate("Schedule/${newFormattedDate}") },
                content = { Text(text = "Schedule", fontFamily = FontTittle, fontSize = 28.sp) })
            Button(
                onClick = { navController.navigate("Diary/${newFormattedDate}") },
                content = { Text(text = "Diary", fontFamily = FontTittle, fontSize = 28.sp) })
            Button(
                onClick = { navController.navigate("Resume/${selectedYear}") },
                content = { Text(text = "Resume", fontFamily = FontTittle, fontSize = 28.sp) })
        }
    }
}


@SuppressLint("SimpleDateFormat")
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}

@SuppressLint("SimpleDateFormat")
fun newFormatDateForDisplay(millis: Long): String {
    val date = Date(millis)
    val dayOfMonth = SimpleDateFormat("dd").format(date)
    val month = SimpleDateFormat("MM").format(date)
    val year = SimpleDateFormat("yyyy").format(date)
    return "$dayOfMonth-$month-$year"
}

