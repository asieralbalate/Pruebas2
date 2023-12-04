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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebas2.ui.theme.SleepColor0
import com.example.pruebas2.ui.theme.SleepColor1
import com.example.pruebas2.ui.theme.SleepColor2
import com.example.pruebas2.ui.theme.SleepColor3
import com.example.pruebas2.ui.theme.SleepColor4
import com.example.pruebas2.ui.theme.SleepColor5
import com.example.pruebas2.ui.theme.SleepColor6
import com.example.pruebas2.ui.theme.SleepColor7
import com.example.pruebas2.ui.theme.SleepColor8
import com.example.pruebas2.ui.theme.SleepColor9

@Composable
fun Sleep(selectedSleepAdjective: Int?, onSleepSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Less than 3 hours", SleepColor0),
        AdjectiveColorPair("4 hours", SleepColor1),
        AdjectiveColorPair("5 hours", SleepColor2),
        AdjectiveColorPair("6 hours", SleepColor3),
        AdjectiveColorPair("7 hours", SleepColor4),
        AdjectiveColorPair("8 hours", SleepColor5),
        AdjectiveColorPair("9 hours", SleepColor6),
        AdjectiveColorPair("10 hours", SleepColor7),
        AdjectiveColorPair("11 hours", SleepColor8),
        AdjectiveColorPair("More than 11 hours", SleepColor9)
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