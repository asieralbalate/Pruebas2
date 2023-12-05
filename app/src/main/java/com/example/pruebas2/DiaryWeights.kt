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
import com.example.pruebas2.ui.theme.SpendColor0
import com.example.pruebas2.ui.theme.SpendColor1
import com.example.pruebas2.ui.theme.SpendColor2
import com.example.pruebas2.ui.theme.SpendColor3
import com.example.pruebas2.ui.theme.SpendColor4
import com.example.pruebas2.ui.theme.SpendColor5
import com.example.pruebas2.ui.theme.SpendColor6
import com.example.pruebas2.ui.theme.SpendColor7
import com.example.pruebas2.ui.theme.SpendColor8
import com.example.pruebas2.ui.theme.SpendColor9
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
        AdjectiveColorPair("Running", WeightsColor0, R.drawable.running),
        AdjectiveColorPair("Climbing", WeightsColor1, R.drawable.climbing),
        AdjectiveColorPair("Weightlifting", WeightsColor2, R.drawable.wheightlifting),
        AdjectiveColorPair("Cycling", WeightsColor3, R.drawable.cycling),
        AdjectiveColorPair("Swimming", WeightsColor4, R.drawable.swimming),
        AdjectiveColorPair("Boxing", WeightsColor5, R.drawable.boxing),
        AdjectiveColorPair("Yoga", WeightsColor6, R.drawable.yoga),
        AdjectiveColorPair("Dancing", WeightsColor7, R.drawable.dancing),
        AdjectiveColorPair("Martial Arts", WeightsColor8, R.drawable.martialarts),
        AdjectiveColorPair("Gymnastics", WeightsColor9, R.drawable.gymnastics)
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
    val brush = Brush.linearGradient(listOf(
        WeightsColor0, WeightsColor1, WeightsColor2, WeightsColor3,
        WeightsColor4, WeightsColor5, WeightsColor6, WeightsColor7, WeightsColor8,
        WeightsColor9
    ))
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(brush).height(55.dp)
        ) {
            Text(text = "What exercise have you done today?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedWeightAdjective,
                onAdjectiveSelected = onWeightSelected,
                adjectivesWithColors = adjectivesWithColors,
                image = image
            )
        }
    }
}