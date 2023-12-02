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
import com.example.pruebas2.ui.theme.FoodColor0
import com.example.pruebas2.ui.theme.FoodColor1
import com.example.pruebas2.ui.theme.FoodColor2
import com.example.pruebas2.ui.theme.FoodColor3
import com.example.pruebas2.ui.theme.FoodColor4
import com.example.pruebas2.ui.theme.FoodColor5
import com.example.pruebas2.ui.theme.FoodColor6
import com.example.pruebas2.ui.theme.FoodColor7
import com.example.pruebas2.ui.theme.FoodColor8
import com.example.pruebas2.ui.theme.FoodColor9

@Composable
fun Food(selectedFoodAdjective: Int?, onFoodSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Italian", FoodColor0),
        AdjectiveColorPair("American", FoodColor1),
        AdjectiveColorPair("Turkish", FoodColor2),
        AdjectiveColorPair("Mexican", FoodColor3),
        AdjectiveColorPair("Japanese", FoodColor4),
        AdjectiveColorPair("Indian", FoodColor5),
        AdjectiveColorPair("Mediterranean", FoodColor6),
        AdjectiveColorPair("Chinese", FoodColor7),
        AdjectiveColorPair("Thai", FoodColor8),
        AdjectiveColorPair("French", FoodColor9)
    )
    Column (Modifier.fillMaxSize()){
        FoodFeedback(selectedFoodAdjective, onFoodSelected, adjectivesWithColors)
    }
}

@Composable
fun FoodFeedback(
    selectedFoodAdjective: Int?,
    onFoodSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>) {

    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)){
            Text(text = "What have you eaten today?", fontSize = 30.sp)
        }
        for ((adjective, color) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedFoodAdjective,
                onAdjectiveSelected = onFoodSelected,
                adjectivesWithColors = adjectivesWithColors
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedFoodAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}