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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.pruebas2.ui.theme.BoxColorSettings
import com.example.pruebas2.ui.theme.DateTittle
import com.example.pruebas2.ui.theme.FontTittle
import com.example.pruebas2.ui.theme.TopBarColor
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun Schedule(selectedDate: String, navController: NavHostController) {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = inputFormat.parse(selectedDate)
    val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val context = LocalContext.current
    val formattedDate = formatDate(localDate)
    var selectedTask by remember { mutableStateOf("") }
    listEvents(context)
    Scaffold(
        topBar = { MyTopBarSchedule(navController, formattedDate) },
        content = {
            Box(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding()
                    )
                    .background(BoxColor)
                    .fillMaxSize()
            ) {
                Column {
                    OutlinedTextField(
                        value = selectedTask,
                        onValueChange = { selectedTask = it },
                        label = {
                            Text("Task or event", fontSize = 20.sp, fontFamily = DateTittle)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .height(100.dp),
                        singleLine = false,
                        minLines = 2,
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                insertOrAddDatabase(selectedDate, selectedTask, context)
                                selectedTask = ""
                            },
                            content = {
                                Text(
                                    text = "Add/Save",
                                    fontFamily = FontTittle,
                                    fontSize = 28.sp
                                )
                            }
                        )
                        Button(
                            onClick = {
                                clearSingleDateDatabase(selectedDate, context)
                                      },
                            content = {
                                Text(
                                    text = "Delete All",
                                    fontFamily = FontTittle,
                                    fontSize = 28.sp
                                )
                            }
                        )
                    }
                    ListEventsSchedule(selectedDate, context)
                }
            }
        }
    )
}

@Composable
fun ListEventsSchedule(selectedDate: String, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val filteredEvents = eventsData.filter { it.dateCal == selectedDate }
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
                            .padding(
                                top = 4.dp,
                                bottom = 4.dp,
                                start = 12.dp,
                                end = 12.dp
                            )
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BoxColorSettings)
                        ) {
                            Text(
                                text = "${index + 1}: $detail",
                                fontSize = 28.sp,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    top = 4.dp
                                )
                            )
                            IconButton(onClick = { deleteOneRowDatabase(selectedDate,detail, context) }) {
                                Image(
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = "schedule delete"
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Row {
                Text(text = "No tasks scheduled", fontSize = 26.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBarSchedule(navController: NavHostController, selectedDate: String) {
    TopAppBar(modifier = Modifier.height(44.dp), colors = TopAppBarColors(
        containerColor = TopBarColor,
        scrolledContainerColor = Color.White,
        navigationIconContentColor = TopBarColor,
        titleContentColor = TopBarColor,
        actionIconContentColor = Color.White
    ), navigationIcon = {
        IconButton(onClick = { navController.navigate("Calendar") }, content = {
            Image(
                painterResource(id = R.drawable.goback), contentDescription = "schedule go back"
            )
        })
    },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = selectedDate,
                    fontSize = 34.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DateTittle
                )
            }

        }
    )
}
