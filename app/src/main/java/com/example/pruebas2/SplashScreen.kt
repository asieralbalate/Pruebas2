package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize() ,
        color = MaterialTheme.colorScheme.background
    ) {
        Image(painter = painterResource(id = R.drawable.splashscreen), contentDescription = null, contentScale = ContentScale.Crop)
    }
    scope.launch {
        delay(3000)
        navController.navigate("Calendar")
    }
}