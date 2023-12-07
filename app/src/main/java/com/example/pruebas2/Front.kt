package com.example.pruebas2


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Front(navController: NavHostController) {
    Surface(Modifier.fillMaxSize().clickable { navController.navigate("Calendar") }) {
        val currentDate = getCurrentDate()
        Text(
            text = currentDate,
            modifier = Modifier.padding(16.dp),
        )
    }
}


