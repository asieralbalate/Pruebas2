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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class AdjectiveColorPair(val adjective: String, val color: Color)

@Composable
fun Day(selectedDiaryAdjective: Int?, onAdjectiveSelected: (Int?) -> Unit){
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
        DayFeedback(selectedDiaryAdjective, onAdjectiveSelected, adjectivesWithColors)
    }
}

@Composable
fun DayFeedback(selectedDiaryAdjective: Int?,
                onAdjectiveSelected: (Int?) -> Unit,
                adjectivesWithColors: List<AdjectiveColorPair>) {

    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)){
            Text(text = "How was your day?", fontSize = 30.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedDiaryAdjective,
                onAdjectiveSelected = onAdjectiveSelected,
                adjectivesWithColors = adjectivesWithColors
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedDiaryAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}

@Composable
fun AdjectiveRow(
    adjective: String,
    color: Color,
    selectedDiaryAdjective: Int?,
    onAdjectiveSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 20.dp)
            .selectable(
                selected = (selectedDiaryAdjective == adjectivesWithColors.indexOfFirst { it.adjective == adjective }),
                onClick = {
                    onAdjectiveSelected(if (selectedDiaryAdjective == adjectivesWithColors.indexOfFirst { it.adjective == adjective }) {
                        null
                    } else {
                        adjectivesWithColors.indexOfFirst { it.adjective == adjective }
                    })
                }
            )
    ) {
        Checkbox(
            checked = (selectedDiaryAdjective == adjectivesWithColors.indexOfFirst { it.adjective == adjective }),
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
        Text(text = adjective, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
    }
}