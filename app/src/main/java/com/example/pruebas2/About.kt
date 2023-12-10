package com.example.pruebas2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.BoxColor
import com.example.pruebas2.ui.theme.BoxColorSettings
import com.example.pruebas2.ui.theme.DateTittle
import com.example.pruebas2.ui.theme.TopBarColor

@Composable
fun About(navController: NavHostController) {
    Scaffold(
        topBar = { MyTopBarInformation(navController) },
        floatingActionButton = {

        },
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
                    Column (modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(BoxColorSettings)
                        .border(BorderStroke(1.dp, Color.Black))
                        )
                    {
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "In this app, the aim is to address")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "the needs of both a diary, checklist,")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "and calendar to remember events.")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "To achieve this, there is a screen for")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "entering diary information, another")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "for querying that information, one")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "for adding and deleting events, and")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "finally, on the main screen, events")
                        Text(fontSize = 30.sp, modifier = Modifier.padding(end = 3.dp, start = 8.dp), text = "for the selected date are listed.")
                    }
                    Column (Modifier.fillMaxSize().padding(5.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom){
                        Text(fontSize = 20.sp,text = "Project done by: Asier Albalate Forner")
                        Text(fontSize = 20.sp,text = "Year: 2023")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBarInformation(navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.height(44.dp),
        colors = TopAppBarColors(
        containerColor = TopBarColor,
        scrolledContainerColor = Color.White,
        navigationIconContentColor = TopBarColor,
        titleContentColor = TopBarColor,
        actionIconContentColor = Color.White
    ),
        navigationIcon = {
        IconButton(onClick = { navController.navigate("Calendar") }, content = {
            Image(
                painterResource(id = R.drawable.goback), contentDescription = "schedule go back"
            )
        }
        )
    },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "About",
                    fontSize = 34.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DateTittle
                )
            }
        }
    )
}