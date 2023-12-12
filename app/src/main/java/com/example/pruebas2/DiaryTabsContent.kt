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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//Function showing the day adjectives and colors
@Composable
fun Day() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brushDay
                )
                .height(55.dp)
        ) {
            Text(text = "How has your day been?", fontSize = 38.sp)
        }
        Column(modifier = Modifier) {
            for ((adjective, color, image) in adjectivesWithColorsDay) {
                AdjectiveRow(
                    adjective = adjective,
                    color = color,
                    selectedDiaryAdjective = selectedDiaryAdjective,
                    adjectivesWithColors = adjectivesWithColorsDay,
                    image = image
                )
            }
        }
    }
}
//Function showing the food adjectives and colors
@Composable
fun Food(){
    Column (Modifier.fillMaxSize()){
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brushFood).height(55.dp)){
            Text(text = "What have you eaten today?", fontSize = 38.sp)
        }
        for ((adjective, color, image) in adjectivesWithColorsFood) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedFoodAdjective,
                adjectivesWithColors = adjectivesWithColorsFood,
                image = image
            )
        }
    }
}
//Function showing the sleep adjectives and colors
@Composable
fun Sleep(){
    Column (Modifier.fillMaxSize()){
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brush = brushSleep, alpha = 0.8f).height(55.dp)){
            Text(text = "How many hours have you slept?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColorsSleep) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedSleepAdjective,
                adjectivesWithColors = adjectivesWithColorsSleep,
                image = image
            )
        }
    }
}
//Function showing the spend adjectives and colors
@Composable
fun Spend() {
    Column(Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(brushSpend).height(55.dp)
        ) {
            Text(text = "How much money have you spent?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColorsSpend) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedSpendAdjective,
                adjectivesWithColors = adjectivesWithColorsSpend,
                image = image
            )
        }
    }
}
//Function showing the steps adjectives and colors
@Composable
fun Steps(){
    Column (Modifier.fillMaxSize()){
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brushSteps).height(55.dp)){
            Text(text = "How many steps have you taken?", fontSize = 32.sp)
        }
        for ((adjective, color, image) in adjectivesWithColorsSteps) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedStepsAdjective,
                adjectivesWithColors = adjectivesWithColorsSteps,
                image = image
            )
        }
    }
}
//Function showing the weather adjectives and colors
@Composable
fun Weather(){
    Column (Modifier.fillMaxSize()){
        Row (horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(brushWeather).height(55.dp)){
            Text(text = "How has the weather been?", fontSize = 38.sp)
        }
        for ((adjective, color, image) in adjectivesWithColorsWeather) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedWeatherAdjective,
                adjectivesWithColors = adjectivesWithColorsWeather,
                image = image
            )
        }
    }
}
//Function showing the sports adjectives and colors
@Composable
fun Weights() {
    Column(Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(brushWeight).height(55.dp)
        ) {
            Text(text = "What exercise have you done today?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColorsWeights) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedWeightAdjective,
                adjectivesWithColors = adjectivesWithColorsWeights,
                image = image
            )
        }
    }
}