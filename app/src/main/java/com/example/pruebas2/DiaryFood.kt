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
fun Food(selectedFoodAdjective: Int?, onFoodSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Italian", FoodColor0, R.drawable.taco),
        AdjectiveColorPair("American", FoodColor1, R.drawable.taco),
        AdjectiveColorPair("Turkish", FoodColor2, R.drawable.taco),
        AdjectiveColorPair("Mexican", FoodColor3, R.drawable.taco),
        AdjectiveColorPair("Japanese", FoodColor4, R.drawable.taco),
        AdjectiveColorPair("Indian", FoodColor5, R.drawable.taco),
        AdjectiveColorPair("Mediterranean", FoodColor6, R.drawable.taco),
        AdjectiveColorPair("Chinese", FoodColor7, R.drawable.taco),
        AdjectiveColorPair("Thai", FoodColor8, R.drawable.taco),
        AdjectiveColorPair("French", FoodColor9, R.drawable.taco)
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
    val brush = Brush.linearGradient(listOf(
        FoodColor0, FoodColor1, FoodColor2, FoodColor3,
        FoodColor4, FoodColor5, FoodColor6, FoodColor7, FoodColor8,
        FoodColor9
    ))
    Column {
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brush).height(55.dp)){
            Text(text = "What have you eaten today?", fontSize = 38.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedFoodAdjective,
                onAdjectiveSelected = onFoodSelected,
                adjectivesWithColors = adjectivesWithColors,
                image = image
            )
        }
    }
}