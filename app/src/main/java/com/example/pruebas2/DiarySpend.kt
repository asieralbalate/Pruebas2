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

@Composable
fun Spend(selectedSpendAdjective: Int?, onSpendSelected: (Int?) -> Unit) {
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
    Column(Modifier.fillMaxSize()) {
        SpendFeedback(selectedSpendAdjective, onSpendSelected, adjectivesWithColors)
    }
}

@Composable
fun SpendFeedback(
    selectedSpendAdjective: Int?,
    onSpendSelected: (Int?) -> Unit,
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
            Text(text = "How much money have you spent?", fontSize = 24.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedSpendAdjective,
                onAdjectiveSelected = onSpendSelected,
                adjectivesWithColors = adjectivesWithColors
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Selected: ${selectedSpendAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}