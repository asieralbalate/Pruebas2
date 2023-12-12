package com.example.pruebas2

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.pruebas2.ui.theme.DateTittle
import com.example.pruebas2.ui.theme.FontTittle
import com.example.pruebas2.ui.theme.RegText
import com.example.pruebas2.ui.theme.TabsColor
import com.example.pruebas2.ui.theme.TopBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Front of the Calendar
@Composable
fun Calendar(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        DatePickerView(navController)
    }
}

// In this function, the calendar is displayed with various added features such as searching for a date by text, etc.
// Additionally, a title with the formatted date is shown, along with three buttons to navigate through different pages.
// If there is any event on the selected date, a list of those events is displayed on the screen.
// If you click on the top-right button, a dropdown menu is displayed with various options.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerView(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
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
                        contentDescription = "headerImage",
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
                        onClick = { showDropDownMenu.value = true },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "settings image",
                            modifier = Modifier
                                .size(60.dp)
                        )
                        DropDownMenu(navController)
                    }
                }
            },
            state = datePickerState,
            showModeToggle = true
        )
        ConfirmationDialogEvents(context, scope, snackbarHostState)
        ConfirmationDialogDiary(context, scope, snackbarHostState)
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
        ListEvents(eventsSelectedDate)
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp), contentAlignment = Alignment.BottomCenter){
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        ) {
            Box(
                modifier = Modifier
                    .background(TabsColor)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Delete completed",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


//In this function we have the structure of the dropdownmenu with it's items.
//If you select the first two rows the program shows a dialog to confirm the input.
//If you choose the las row yo navigate to the about page
@Composable
fun DropDownMenu(navController: NavHostController) {
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
                    painter = painterResource(R.drawable.eventsdropdownmenu),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "dropdown events",
                    modifier = Modifier.size(40.dp)
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
                    painter = painterResource(R.drawable.diarydropdownmenu),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "dropdown diary",
                    modifier = Modifier.size(40.dp)
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
                navController.navigate("About")
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.infodropdownmenu),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "dropdown info",
                    modifier = Modifier.size(40.dp)
                )
            },
            text = {
                Text(
                    text = "About",
                    fontFamily = FontTittle,
                    fontSize = 26.sp
                )
            }
        )
    }
}

//This is the dialog to confirm de delete of the eventsCalendar database
@Composable
fun ConfirmationDialogEvents(context: Context, scope: CoroutineScope, snackbarHostState: SnackbarHostState ) {
    if (showDialogEvents.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AlertDialog(
                containerColor = BoxColorSettings,
                onDismissRequest = {
                    showDialogEvents.value = false
                },
                title = {
                    Text(
                        text = "CONFIRMATION",
                        fontFamily = DateTittle,
                        fontSize = 26.sp
                    )
                },
                text = {
                    Text(
                        text = "Are you sure you want to clear all EVENTS from the database?",
                        fontFamily = DateTittle,
                        fontSize = 20.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialogEvents.value = false
                            deleteAllCalendarEvents(context)
                            scope.launch { snackbarHostState.showSnackbar("Saved successfully") }
                        },
                        colors = buttonColors(containerColor = TabsColor),
                    ) {
                        Text(
                            text = "YES",
                            fontFamily = DateTittle,
                            fontSize = 16.sp
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialogEvents.value = false
                        },
                        colors = buttonColors(containerColor = TabsColor),
                    ) {
                        Text(
                            text = "NO",
                            fontFamily = DateTittle,
                            fontSize = 16.sp
                        )
                    }
                }
            )
        }
    }
}

//This is the dialog to confirm de delete of the diary calendar database
@Composable
fun ConfirmationDialogDiary(context: Context, scope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    if (showDialogDiary.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AlertDialog(
                containerColor = BoxColorSettings,
                onDismissRequest = {
                    showDialogDiary.value = false
                },
                title = {
                    Text(
                        text = "CONFIRMATION",
                        fontFamily = DateTittle,
                        fontSize = 26.sp
                    )
                },
                text = {
                    Text(
                        fontFamily = DateTittle,
                        fontSize = 20.sp,
                        text = "Are you sure you want to clear all DIARY records from the database?"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialogDiary.value = false
                            deleteAllDiaryRecord(context)
                            scope.launch { snackbarHostState.showSnackbar("Saved successfully") }
                        },
                        colors = buttonColors(containerColor = TabsColor),
                    ) {
                        Text(
                            text = "Yes",
                            fontFamily = DateTittle,
                            fontSize = 16.sp
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialogDiary.value = false
                        },
                        colors = buttonColors(containerColor = TabsColor),
                    ) {
                        Text(
                            text = "No", fontFamily = DateTittle,
                            fontSize = 16.sp
                        )
                    }
                }
            )
        }
    }
}

// This function list the events of the database and filters it on the date selected
@Composable
fun ListEvents(eventsSelectedDate: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            ,
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
                        colors= CardDefaults.cardColors(containerColor = BoxColorSettings),
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
