package com.example.pruebas2

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebas2.ui.theme.StepsColor0
import com.example.pruebas2.ui.theme.StepsColor1
import com.example.pruebas2.ui.theme.StepsColor2
import com.example.pruebas2.ui.theme.StepsColor3
import com.example.pruebas2.ui.theme.StepsColor4
import com.example.pruebas2.ui.theme.StepsColor5
import com.example.pruebas2.ui.theme.StepsColor6
import com.example.pruebas2.ui.theme.StepsColor7
import com.example.pruebas2.ui.theme.StepsColor8
import com.example.pruebas2.ui.theme.StepsColor9
import com.example.pruebas2.ui.theme.WeatherColor0
import com.example.pruebas2.ui.theme.WeatherColor1
import com.example.pruebas2.ui.theme.WeatherColor2
import com.example.pruebas2.ui.theme.WeatherColor3
import com.example.pruebas2.ui.theme.WeatherColor4
import com.example.pruebas2.ui.theme.WeatherColor5
import com.example.pruebas2.ui.theme.WeatherColor6
import com.example.pruebas2.ui.theme.WeatherColor7
import com.example.pruebas2.ui.theme.WeatherColor8
import com.example.pruebas2.ui.theme.WeatherColor9

@Composable
fun Steps(selectedStepsAdjective: Int?, onStepsSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("-100", StepsColor0, R.drawable.lesshundred),
        AdjectiveColorPair("100 - 500", StepsColor1, R.drawable.fivehundred),
        AdjectiveColorPair("500 - 1k", StepsColor2, R.drawable.thousand),
        AdjectiveColorPair("1k - 2k", StepsColor3, R.drawable.twothous),
        AdjectiveColorPair("2k - 5k", StepsColor4, R.drawable.fivethous),
        AdjectiveColorPair("5k - 10k", StepsColor5, R.drawable.tenthous),
        AdjectiveColorPair("10k - 15k", StepsColor6, R.drawable.fifteenthous),
        AdjectiveColorPair("15k - 20k", StepsColor7, R.drawable.twentythous),
        AdjectiveColorPair("20k - 30k", StepsColor8, R.drawable.thirtythous),
        AdjectiveColorPair("+30k", StepsColor9, R.drawable.morethirtythous)
    )
    Column (Modifier.fillMaxSize()){
        StepsFeedback(selectedStepsAdjective, onStepsSelected, adjectivesWithColors)
    }
}

@Composable
fun StepsFeedback(selectedStepsAdjective: Int?,
                  onStepsSelected: (Int?) -> Unit,
                  adjectivesWithColors: List<AdjectiveColorPair> ) {
    val brush = Brush.linearGradient(listOf(
        StepsColor0, StepsColor1, StepsColor2, StepsColor3,
        StepsColor4, StepsColor5, StepsColor6, StepsColor7, StepsColor8,
        StepsColor9
    ))
    Column {
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brush).height(55.dp)){
            Text(text = "How many steps have you taken?", fontSize = 32.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedStepsAdjective,
                onAdjectiveSelected = onStepsSelected,
                adjectivesWithColors = adjectivesWithColors,
                image = image
            )
        }
    }
}