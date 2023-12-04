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
    val adjectivesWithColors1 = listOf(
        AdjectiveColorPair1("Fantastic", DiaryColor0, R.drawable.fantastic),
        AdjectiveColorPair1("Terrible", DiaryColor1, R.drawable.terrible),
        AdjectiveColorPair1("Productive", DiaryColor2, R.drawable.productive),
        AdjectiveColorPair1("Challenging", DiaryColor3, R.drawable.challenging),
        AdjectiveColorPair1("Relaxing", DiaryColor4, R.drawable.relaxing),
        AdjectiveColorPair1("Exciting", DiaryColor5, R.drawable.exciting),
        AdjectiveColorPair1("Hectic", DiaryColor6, R.drawable.hectic),
        AdjectiveColorPair1("Joyful", DiaryColor7, R.drawable.joyful),
        AdjectiveColorPair1("Frustrating", DiaryColor8, R.drawable.frustrating),
        AdjectiveColorPair1("Rewarding", DiaryColor9, R.drawable.rewarding)
    )
    Column (Modifier.fillMaxSize()){
        DayFeedback(selectedDiaryAdjective, onAdjectiveSelected, adjectivesWithColors1)
    }
}

@Composable
fun DayFeedback(
    selectedDiaryAdjective: Int?,
    onAdjectiveSelected: (Int?) -> Unit,
    adjectivesWithColors1: List<AdjectiveColorPair1>
) {

    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)){
            Text(text = "How has your day been?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors1) {
            AdjectiveRow1(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedDiaryAdjective,
                onAdjectiveSelected = onAdjectiveSelected,
                adjectivesWithColors = adjectivesWithColors1,
                image = image
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedDiaryAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}

