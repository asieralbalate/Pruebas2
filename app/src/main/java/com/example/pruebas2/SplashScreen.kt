package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.BoxColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize().background(BoxColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashscreen),
            contentDescription = "splash image",
            modifier = Modifier.fillMaxSize(),
            )
    }
    scope.launch {
        delay(3000)
        navController.navigate("Calendar")
    }
}