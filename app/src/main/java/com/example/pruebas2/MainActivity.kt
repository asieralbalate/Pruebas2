package com.example.pruebas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebas2.ui.theme.Pruebas2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pruebas2Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Calendar") {
                    composable("Front") { Front(navController) }
                    composable("Calendar") { Calendar(navController) }
                    composable(
                        route = "Diary/{selectedDate}",
                        arguments = listOf(navArgument("selectedDate") { type = NavType.StringType })
                    ) { backStackEntry ->
                        Diary(backStackEntry.arguments?.getString("selectedDate") ?: "", navController)
                    }

                    composable("Resume") { Resume(navController) }
                }

            }
        }
    }
}



