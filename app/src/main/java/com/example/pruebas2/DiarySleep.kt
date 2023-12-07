package com.example.pruebas2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebas2.ui.theme.*

@Composable
fun Sleep(selectedSleepAdjective: Int?, onSleepSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("-4 hours", SleepColor0, R.drawable.lessthreeh),
        AdjectiveColorPair("4 hours", SleepColor1, R.drawable.fourh),
        AdjectiveColorPair("5 hours", SleepColor2, R.drawable.fiveh),
        AdjectiveColorPair("6 hours", SleepColor3, R.drawable.sixh),
        AdjectiveColorPair("7 hours", SleepColor4, R.drawable.sevenh),
        AdjectiveColorPair("8 hours", SleepColor5, R.drawable.eighth),
        AdjectiveColorPair("9 hours", SleepColor6, R.drawable.nineh),
        AdjectiveColorPair("10 hours", SleepColor7, R.drawable.tenh),
        AdjectiveColorPair("11 hours", SleepColor8, R.drawable.elevenh),
        AdjectiveColorPair("+11 hours", SleepColor9, R.drawable.morelevenh)
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
    val brush = Brush.linearGradient(listOf(
         SleepColor0, SleepColor4,SleepColor1, SleepColor2, SleepColor3,
         SleepColor5, SleepColor8,
        SleepColor9,  SleepColor6,SleepColor7
    ))
    Column {
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brush = brush, alpha = 0.8f).height(55.dp)){
            Text(text = "How many hours have you slept?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedSleepAdjective,
                onAdjectiveSelected = onSleepSelected,
                adjectivesWithColors = adjectivesWithColors,
                image = image
            )
        }
    }
}