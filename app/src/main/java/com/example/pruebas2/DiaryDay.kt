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
import com.example.pruebas2.ui.theme.DiaryColor0
import com.example.pruebas2.ui.theme.DiaryColor1
import com.example.pruebas2.ui.theme.DiaryColor2
import com.example.pruebas2.ui.theme.DiaryColor3
import com.example.pruebas2.ui.theme.DiaryColor4
import com.example.pruebas2.ui.theme.DiaryColor5
import com.example.pruebas2.ui.theme.DiaryColor6
import com.example.pruebas2.ui.theme.DiaryColor7
import com.example.pruebas2.ui.theme.DiaryColor8
import com.example.pruebas2.ui.theme.DiaryColor9


@Composable
fun Day(selectedDiaryAdjective: Int?, onAdjectiveSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Fantastic", DiaryColor0),
        AdjectiveColorPair("Terrible", DiaryColor1),
        AdjectiveColorPair("Productive", DiaryColor2),
        AdjectiveColorPair("Challenging", DiaryColor3),
        AdjectiveColorPair("Relaxing", DiaryColor4),
        AdjectiveColorPair("Exciting", DiaryColor5),
        AdjectiveColorPair("Hectic", DiaryColor6),
        AdjectiveColorPair("Joyful", DiaryColor7),
        AdjectiveColorPair("Frustrating", DiaryColor8),
        AdjectiveColorPair("Rewarding", DiaryColor9)
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
            Text(text = "How has your day been?", fontSize = 30.sp)
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

