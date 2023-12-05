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
fun Weather(selectedWeatherAdjective: Int?, onWeatherSelected: (Int?) -> Unit){
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("Sunny", WeatherColor0, R.drawable.sunny),
        AdjectiveColorPair("Rainy", WeatherColor1, R.drawable.rainy),
        AdjectiveColorPair("Cloudy", WeatherColor2, R.drawable.cloudy),
        AdjectiveColorPair("Windy", WeatherColor3, R.drawable.windy),
        AdjectiveColorPair("Snowy", WeatherColor4, R.drawable.snowy),
        AdjectiveColorPair("Stormy", WeatherColor5, R.drawable.stormy),
        AdjectiveColorPair("Foggy", WeatherColor6, R.drawable.foggy),
        AdjectiveColorPair("Clear", WeatherColor7, R.drawable.clear),
        AdjectiveColorPair("Warm", WeatherColor8, R.drawable.warm),
        AdjectiveColorPair("Cold", WeatherColor9, R.drawable.cold)
    )
    Column (Modifier.fillMaxSize()){
        WeatherFeedback(selectedWeatherAdjective, onWeatherSelected, adjectivesWithColors)
    }
}

@Composable
fun WeatherFeedback(selectedWeatherAdjective: Int?, onWeatherSelected: (Int?) -> Unit, adjectivesWithColors: List<AdjectiveColorPair>) {
    Column {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text = "How has the weather been?", fontSize = 38.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedWeatherAdjective,
                onAdjectiveSelected = onWeatherSelected,
                adjectivesWithColors = adjectivesWithColors,
                image = image
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()){
            Text(text ="Selected: ${selectedWeatherAdjective ?: "None"}", fontSize = 20.sp)
        }
    }
}