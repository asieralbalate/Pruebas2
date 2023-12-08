package com.example.pruebas2

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.BoxColor
import com.example.pruebas2.ui.theme.BoxColorSettings
import com.example.pruebas2.ui.theme.FontTittle
import com.example.pruebas2.ui.theme.RegText
import com.example.pruebas2.ui.theme.TabsColor
import com.example.pruebas2.ui.theme.TopBarColor
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
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
    val context = LocalContext.current
    listEvents(context)
    val currentSelectedDateMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val datePickerState =
        rememberDatePickerState(
            currentSelectedDateMillis,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {

                    return true
                }
            }
        )
    val newFormattedDate = datePickerState.selectedDateMillis?.let {
        newFormatDateForDisplay(it)
    }
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    val eventsSelectedDate = newFormattedDate
    val selectedYear = newFormattedDate?.split("-")?.get(2)
    Column(
        modifier = Modifier
            .background(BoxColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally
    ) {
        DatePicker(
            colors = DatePickerDefaults.colors(BoxColor),
            dateFormatter = DatePickerDefaults.dateFormatter(),
            headline = {
                if (selectedDate == null) {
                    Text(text = "No date selected", modifier = Modifier.padding(start = 10.dp))
                } else {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                            .size(50.dp)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "CalenDiary",
                        color = Color.White,
                        fontSize = 60.sp,
                        fontFamily = FontTittle
                    )
                    IconButton(
                        onClick = { showDropDownMenu.value = true }, modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                        )
                        DropDownMenu()
                    }
                }
            },
            state = datePickerState,
            showModeToggle = true
        )
        ConfirmationDialogEvents(context)
        ConfirmationDialogDiary(context)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val filteredEvents = eventsData.filter { it.dateCal == eventsSelectedDate }
            if (filteredEvents.isNotEmpty()) {
                Row {
                    Text(text = "Scheduled Tasks", fontSize = 26.sp)
                }
                filteredEvents.forEach { event ->
                    val eventDetails = event.event.split("&&")
                    eventDetails.forEachIndexed { index, detail ->
                        Card(
                            elevation = CardDefaults.cardElevation(5.dp),
                            modifier = Modifier
                                .padding(top = 4.dp, bottom = 4.dp, start = 12.dp, end = 12.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${index + 1}: $detail",
                                    fontSize = 26.sp,
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        top = 4.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropDownMenu(){
    DropdownMenu(
        expanded = showDropDownMenu.value,
        onDismissRequest = {
            showDropDownMenu.value = false
        },
        modifier = Modifier.background(BoxColorSettings)
    ) {
        DropdownMenuItem(
            onClick = {
                showDialogEvents.value = true
                showDropDownMenu.value = false
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.settings),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            },
            text = {
                Text(
                    text = "Clear Database Events",
                    fontFamily = FontTittle,
                    fontSize = 26.sp
                )
            }
        )
        DropdownMenuItem(
            onClick = {
                showDialogDiary.value = true
                showDropDownMenu.value = false
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.settings),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            },
            text = {
                Text(
                    text = "Clear Diary Data",
                    fontFamily = FontTittle,
                    fontSize = 26.sp
                )
            }
        )
        DropdownMenuItem(
            onClick = {
                showDropDownMenu.value = false
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.settings),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            },
            text = {
                Text(
                    text = "Information",
                    fontFamily = FontTittle,
                    fontSize = 26.sp
                )
            }
        )
    }
}

@Composable
fun ConfirmationDialogEvents(context: Context) {
    if (showDialogEvents.value){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            AlertDialog(
                containerColor = BoxColorSettings,
                onDismissRequest = {
                    showDialogEvents.value = false
                },
                title = {
                    Text(text = "Confirmation")
                },
                text = {
                    Text(text = "Are you sure you want to clear all records from the database?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialogEvents.value = false
                            deleteAllCalendarEvents(context)
                        },
                        colors = buttonColors(containerColor = TabsColor),
                    ) {
                        Text(text = "Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialogEvents.value = false
                        },
                        colors = buttonColors(contentColor = TabsColor),
                    ) {
                        Text(text = "No")
                    }
                }
            )
        }
    }
}

@Composable
fun ConfirmationDialogDiary(context: Context) {
    if (showDialogDiary.value){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            AlertDialog(
                containerColor = BoxColorSettings,
                onDismissRequest = {
                    showDialogDiary.value = false
                },
                title = {
                    Text(text = "Confirmation")
                },
                text = {
                    Text(text = "Are you sure you want to clear all records from the database?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialogDiary.value = false
                            deleteAllDiaryRecord(context)
                        },
                        colors = buttonColors(containerColor = TabsColor),
                    ) {
                        Text(text = "Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialogDiary.value = false
                        },
                        colors = buttonColors(contentColor = TabsColor),
                    ) {
                        Text(text = "No")
                    }
                }
            )
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

fun getCurrentDate(): String {
    val calendar = java.util.Calendar.getInstance()
    val formatter = SimpleDateFormat("d", Locale.getDefault())
    val dayOfMonth = formatter.format(calendar.time).toInt()
    val daySuffix = getDayOfMonthSuffix(dayOfMonth)
    val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    return "${dayOfMonth}$daySuffix of ${dateFormat.format(calendar.time)}"
}

fun getDayOfMonthSuffix(n: Int): String {
    return when {
        n in 11..13 -> "th"
        n % 10 == 1 -> "st"
        n % 10 == 2 -> "nd"
        n % 10 == 3 -> "rd"
        else -> "th"
    }
}