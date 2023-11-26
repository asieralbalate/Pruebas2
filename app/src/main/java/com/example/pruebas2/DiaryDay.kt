package com.example.pruebas2

import androidx.compose.foundation.background
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

@Composable
fun Day(){
    Column (Modifier.fillMaxSize()){
        DayFeedback()
    }
}

@Composable
fun DayFeedback() {
    var selectedAdjective by remember { mutableStateOf<String?>(null) }

    val adjectivesWithColors = listOf(
        "Fantastic" to Color.Yellow,
        "Terrible" to Color.Gray,
        "Productive" to Color.Green,
        "Challenging" to Color.Red,
        "Relaxing" to Color.Blue,
        "Exciting" to Color.Black,
        "Hectic" to Color.Red,
        "Joyful" to Color.Magenta,
        "Frustrating" to Color.Magenta,
        "Rewarding" to Color(0xFFFFD700) // Gold
    )

    Column {
        for ((adjective, color) in adjectivesWithColors) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .selectable(
                        selected = (selectedAdjective == adjective),
                        onClick = {
                            selectedAdjective = if (selectedAdjective == adjective) {
                                null
                            } else {
                                adjective
                            }
                        }
                    )
            ) {
                Checkbox(
                    checked = (selectedAdjective == adjective),
                    onCheckedChange = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = adjective, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Selected Adjective: ${selectedAdjective ?: "None"}")
    }
}