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
        AdjectiveColorPair("Fantastic", DiaryColor0, R.drawable.fantastic),
        AdjectiveColorPair("Terrible", DiaryColor1, R.drawable.terrible),
        AdjectiveColorPair("Productive", DiaryColor2, R.drawable.productive),
        AdjectiveColorPair("Challenging", DiaryColor3, R.drawable.challenging),
        AdjectiveColorPair("Relaxing", DiaryColor4, R.drawable.relaxing),
        AdjectiveColorPair("Exciting", DiaryColor5, R.drawable.exciting),
        AdjectiveColorPair("Hectic", DiaryColor6, R.drawable.hectic),
        AdjectiveColorPair("Joyful", DiaryColor7, R.drawable.joyful),
        AdjectiveColorPair("Frustrating", DiaryColor8, R.drawable.frustrating),
        AdjectiveColorPair("Rewarding", DiaryColor9, R.drawable.rewarding)
    )
    Column (
        Modifier
            .fillMaxSize()
            ){
        DayFeedback(selectedDiaryAdjective, onAdjectiveSelected, adjectivesWithColors)
    }
}

@Composable
fun DayFeedback(
    selectedDiaryAdjective: Int?,
    onAdjectiveSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>
) {
    val brush = Brush.linearGradient(listOf(
        DiaryColor0, DiaryColor1, DiaryColor2, DiaryColor3,
        DiaryColor4, DiaryColor5, DiaryColor6, DiaryColor7, DiaryColor8,
        DiaryColor9))
    Column (Modifier.fillMaxSize()){
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .background(
                brush
            ).height(55.dp)){
            Text(text = "How has your day been?", fontSize = 38.sp)
        }
        Column (modifier = Modifier){
            for ((adjective, color , image)in adjectivesWithColors) {
                AdjectiveRow(
                    adjective = adjective,
                    color = color,
                    selectedDiaryAdjective = selectedDiaryAdjective,
                    onAdjectiveSelected = onAdjectiveSelected,
                    adjectivesWithColors = adjectivesWithColors,
                    image = image
                )
            }
        }
    }
}

