package com.example.pruebas2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sleep(selectedSleepAdjective: Int?, onSleepSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Fantastic", Color.Yellow),
        AdjectiveColorPair("Terrible", Color.Gray),
        AdjectiveColorPair("Productive", Color.Green),
        AdjectiveColorPair("Challenging", Color.Red),
        AdjectiveColorPair("Relaxing", Color.Blue),
        AdjectiveColorPair("Exciting", Color.Black),
        AdjectiveColorPair("Hectic", Color.Red),
        AdjectiveColorPair("Joyful", Color.Magenta),
        AdjectiveColorPair("Frustrating", Color.Magenta),
        AdjectiveColorPair("Rewarding", Color(0xFFFFD700)) // Gold
    )
    Column (Modifier.fillMaxSize()){
        SleepFeedback(selectedSleepAdjective, onSleepSelected, adjectivesWithColors)
    }
}

@Composable
fun SleepFeedback(
    selectedSleepAdjective: Int?,
    onSleepSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>) {

    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)){
            Text(text = "How many hours have you slept?", fontSize = 25.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedSleepAdjective,
                onAdjectiveSelected = onSleepSelected,
                adjectivesWithColors = adjectivesWithColors
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedSleepAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}