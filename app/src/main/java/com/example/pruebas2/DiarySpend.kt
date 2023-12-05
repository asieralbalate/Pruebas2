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

@Composable
fun Spend(selectedSpendAdjective: Int?, onSpendSelected: (Int?) -> Unit) {
    val adjectivesWithColors = listOf(
        AdjectiveColorPair("-1€", SpendColor0, R.drawable.one),
        AdjectiveColorPair("1 - 5 €", SpendColor1,R.drawable.five),
        AdjectiveColorPair("5 - 10 €", SpendColor2, R.drawable.ten),
        AdjectiveColorPair("10 - 20 €", SpendColor3, R.drawable.twenty),
        AdjectiveColorPair("20 - 50 €", SpendColor4, R.drawable.fifty),
        AdjectiveColorPair("50 - 100 €", SpendColor5, R.drawable.hundredk),
        AdjectiveColorPair("100 - 200 €", SpendColor6, R.drawable.twohundk),
        AdjectiveColorPair("200 - 500 €", SpendColor7, R.drawable.fivehundk),
        AdjectiveColorPair("500 - 1.000 €", SpendColor8, R.drawable.thousk),
        AdjectiveColorPair("+1.000 €", SpendColor9, R.drawable.morethousk)
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

        ) {
            Text(text = "How much money have you spent?", fontSize = 30.sp)
        }
        for ((adjective, color, image) in adjectivesWithColors) {
            AdjectiveRow(
                adjective = adjective,
                color = color,
                selectedDiaryAdjective = selectedSpendAdjective,
                onAdjectiveSelected = onSpendSelected,
                adjectivesWithColors = adjectivesWithColors,
                image = image
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