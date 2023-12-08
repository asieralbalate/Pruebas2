package com.example.pruebas2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebas2.ui.theme.Pruebas2Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Pruebas2Theme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()

                NavHost(navController = navController, startDestination = "SplashScreen") {
                    composable("Front") { Front(navController) }
                    composable("SplashScreen") { SplashScreen(navController) }
                    composable("Calendar") { Calendar(navController) }
                    composable(
                        route = "Diary/{selectedDate}",
                        arguments = listOf(navArgument("selectedDate") { type = NavType.StringType })
                    ) { backStackEntry ->
                        Diary(backStackEntry.arguments?.getString("selectedDate") ?: "", navController)
                    }
                    composable(
                        route = "Resume/{selectedYear}",
                        arguments = listOf(navArgument("selectedYear") { type = NavType.StringType })
                    ) { backStackEntry ->
                        Resume(backStackEntry.arguments?.getString("selectedYear") ?: "", navController)
                    }
                    composable(
                        route = "Schedule/{selectedDate}",
                        arguments = listOf(navArgument("selectedDate") { type = NavType.StringType })
                    ) { backStackEntry ->
                        Schedule(backStackEntry.arguments?.getString("selectedDate") ?: "", navController)
                    }
                }
            }
        }
    }
}



