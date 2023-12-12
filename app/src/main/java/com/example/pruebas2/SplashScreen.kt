package com.example.pruebas2

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.pruebas2.ui.theme.BoxColor
import kotlinx.coroutines.delay


// Class of the route
sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route =
    "splash_screen")
    object MainScreenDest : DestinationScreen(route = "Calendar")
}

// Function to do the animation of the splash
@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    navController: NavController,
    durationMillisAnimation: Int,
    delayScreen: Long
) {
    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)

        navController.navigate(route =
        DestinationScreen.MainScreenDest.route) {
            popUpTo(route =
            DestinationScreen.SplashScreenDest.route) {
                inclusive = true
            }
        }
    }
}

//Function to desing the splash
@Composable
fun DesignSplashScreen(
    imagePainter: Painter
) {
    Box(
        modifier = Modifier.fillMaxSize().background(BoxColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "splash image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
    }
}

//Function to show the splash and do the animation
@Composable
fun SplashScreen(navController: NavController) {
    val scaleAnimation: Animatable<Float, AnimationVector1D> =
        remember { Animatable(initialValue = 0f) }
    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        navController = navController,
        durationMillisAnimation = 1500,
        delayScreen = 1500L
    )
    DesignSplashScreen(
        imagePainter = painterResource(id =
        R.drawable.splashscreen),
    )
}