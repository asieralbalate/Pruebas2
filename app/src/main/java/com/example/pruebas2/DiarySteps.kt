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

@Composable
fun Steps(selectedStepsAdjective: Int?, onStepsSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Less than 100", StepsColor0),
        AdjectiveColorPair("100 - 500", StepsColor1),
        AdjectiveColorPair("500 - 1.000", StepsColor2),
        AdjectiveColorPair("1.000 - 2.000", StepsColor3),
        AdjectiveColorPair("2.000 - 5.000", StepsColor4),
        AdjectiveColorPair("5.000 - 10.000", StepsColor5),
        AdjectiveColorPair("10.000 - 15.000", StepsColor6),
        AdjectiveColorPair("15.000 - 20.000", StepsColor7),
        AdjectiveColorPair("20.000 - 30.000", StepsColor8),
        AdjectiveColorPair("More than 30k", StepsColor9)
    )
    Column (Modifier.fillMaxSize()){
        StepsFeedback(selectedStepsAdjective, onStepsSelected, adjectivesWithColors)
    }
}

@Composable
fun StepsFeedback(selectedStepsAdjective: Int?,
                  onStepsSelected: (Int?) -> Unit,
                  adjectivesWithColors: List<AdjectiveColorPair> ) {

    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)){
            Text(text = "How many steps have you taken?", fontSize = 25.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedStepsAdjective,
                onAdjectiveSelected = onStepsSelected,
                adjectivesWithColors = adjectivesWithColors
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedStepsAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}