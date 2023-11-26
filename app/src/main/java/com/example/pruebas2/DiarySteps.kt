package com.example.pruebas2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Steps(){
    Column (Modifier.fillMaxSize()){
        StepsFeedback()
    }
}

@Composable
fun StepsFeedback() {
    var selectedWeatherAdjective by remember { mutableStateOf<String?>(null) }

    val adjectivesWithColors = listOf(
        "Sunny" to Color.Yellow,
        "Cloudy" to Color.Gray,
        "Rainy" to Color.Green,
        "Snowy" to Color.Red,
        "Windy" to Color.Blue,
        "Hot" to Color.Black,
        "Cold" to Color.Red,
        "Warm" to Color.Magenta,
        "Chaotic" to Color.Magenta,
        "Foggy" to Color(0xFFFFD700) // Gold
    )

    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)){
            Text(text = "How many steps did you do?", fontSize = 28.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 20.dp)
                    .selectable(
                        selected = (selectedWeatherAdjective == adjective),
                        onClick = {
                            selectedWeatherAdjective = if (selectedWeatherAdjective == adjective) {
                                null
                            } else {
                                adjective
                            }
                        }
                    )
            ) {
                Checkbox(
                    checked = (selectedWeatherAdjective == adjective),
                    onCheckedChange = null,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = adjective, fontSize = 20.sp ,modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedWeatherAdjective ?: "None"}", fontSize = 20.sp)
        }

    }
}