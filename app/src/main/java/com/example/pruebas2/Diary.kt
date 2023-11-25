package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Diary(navController: NavHostController) {
    Scaffold(topBar = {  MyNavigationBar(navController)}, content = {
        Box(
            modifier = Modifier.padding(
                top = it.calculateTopPadding()
            )
        ) { Text(text = "Hola") }
    })
}


@Composable
fun MyNavigationBar(
    navController: NavHostController
) {
    var selectedItem  by remember { mutableStateOf(5) }
    val items = listOf("MyPhotos", "CoffeeShops", "ElSol", "hola", "si")

    NavigationBar(modifier = Modifier
        .height(80.dp)   ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (selectedItem) {
                        0 -> navController.navigate("PortadaFotos")
                        1 -> navController.navigate("PortadaCoffee")
                        2 -> navController.navigate("PortadaElSol")
                        3 -> navController.navigate("PortadaElSol")
                        4 -> navController.navigate("PortadaElSol")
                    }
                },
                icon = {
                    when (item) {
                        "MyPhotos" -> Icon(
                            imageVector = Icons.Filled.AccountBox,
                            contentDescription = item
                        )

                        "CoffeeShops" -> Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = item
                        )

                        "ElSol" -> Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = item
                        )

                        "ElSol" -> Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = item
                        )

                        "ElSol" -> Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = item
                        )
                    }
                })
        }
    }
}