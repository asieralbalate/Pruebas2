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
import com.example.pruebas2.ui.theme.WeightsColor0
import com.example.pruebas2.ui.theme.WeightsColor1
import com.example.pruebas2.ui.theme.WeightsColor2
import com.example.pruebas2.ui.theme.WeightsColor3
import com.example.pruebas2.ui.theme.WeightsColor4
import com.example.pruebas2.ui.theme.WeightsColor5
import com.example.pruebas2.ui.theme.WeightsColor6
import com.example.pruebas2.ui.theme.WeightsColor7
import com.example.pruebas2.ui.theme.WeightsColor8
import com.example.pruebas2.ui.theme.WeightsColor9

@Composable
fun Weights(selectedWeightAdjective: Int?, onWeightSelected: (Int?) -> Unit) {
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Running", WeightsColor0),
        AdjectiveColorPair("Climbing", WeightsColor1),
        AdjectiveColorPair("Weightlifting", WeightsColor2),
        AdjectiveColorPair("Cycling", WeightsColor3),
        AdjectiveColorPair("Swimming", WeightsColor4),
        AdjectiveColorPair("Boxing", WeightsColor5),
        AdjectiveColorPair("Yoga", WeightsColor6),
        AdjectiveColorPair("Dancing", WeightsColor7),
        AdjectiveColorPair("Martial Arts", WeightsColor8),
        AdjectiveColorPair("Gymnastics", WeightsColor9)
    )

    Column(Modifier.fillMaxSize()) {
        WeightFeedback(selectedWeightAdjective, onWeightSelected, adjectivesWithColors)
    }
}

@Composable
fun WeightFeedback(
    selectedWeightAdjective: Int?,
    onWeightSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>
) {

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        ) {
            Text(text = "What exercise have you done today?", fontSize = 23.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedWeightAdjective,
                onAdjectiveSelected = onWeightSelected,
                adjectivesWithColors = adjectivesWithColors
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Selected: ${selectedWeightAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}