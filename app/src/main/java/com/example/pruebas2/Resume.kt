package com.example.pruebas2

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.*
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun Resume(selectedYear: String, navController: NavHostController) {
    var dataMap by remember { mutableStateOf<MutableMap<String, IntArray>>(mutableMapOf()) }
    val context = LocalContext.current

    LaunchedEffect(selectedYear, context) {
        checkDatabase(context) { fetchedDataMap ->
            dataMap = fillMap(selectedYear, fetchedDataMap)
        }
    }

    Scaffold(
        topBar = { MyResTopBar(navController, selectedYear) },
        floatingActionButton = { },
        floatingActionButtonPosition = FabPosition.End,
        content = {

            Box(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding()
                    )
                    .background(BoxColor)
            ) {
                Log.d("Resume", "DataMap size: ${dataMap.size}")
                MyResTabs(dataMap) }
        }
    )
}




fun fillMap(selectedYear: String, fetchedDataMap: MutableMap<String, IntArray>): MutableMap<String, IntArray> {
    val rightYearMap: MutableMap<String, IntArray> = mutableMapOf()
    fetchedDataMap.forEach { (dateCal, values) ->
        val year = dateCal.split("-")[2]
        if (year == selectedYear) {
            rightYearMap[dateCal] = values
        }
    }
    return rightYearMap
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyResTabs(dataMap: MutableMap<String, IntArray>) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { 7 }
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = TabsColor,
            contentColor = Color.White,
            ) {
            imageResourcesScheduleTabs.forEachIndexed { index, imageResource ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(page = index) } },
                    icon = {
                        Image(
                            painter = painterResource(id = imageResource),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                            modifier = Modifier
                                .width(60.dp)
                        )
                    }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> ResumeDay(dataMap)
                1 -> ResumeWeather(dataMap)
                2 -> ResumeSteps(dataMap)
                3 -> ResumeSpend(dataMap)
                4 -> ResumeWeights(dataMap)
                5 -> ResumeFood(dataMap)
                6 -> ResumeSleep(dataMap)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyResTopBar(navController: NavHostController, selectedYear: String) {

    var selectedMenuItem by remember { mutableStateOf<Pair<Int, String>?>(null) }





    TopAppBar(
        modifier = Modifier.height(44.dp),
        colors = TopAppBarColors(
            containerColor = TopBarColor,
            scrolledContainerColor = Color.White,
            navigationIconContentColor = TopBarColor,
            titleContentColor = TopBarColor,
            actionIconContentColor = Color.White
        ), actions = {
            IconButton(onClick = { isMenuVisibleResTopBar.value = true }) {
                Image(
                    painterResource(id = R.drawable.actionsresume), contentDescription = null
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DropdownMenu(
                    expanded = isMenuVisibleResTopBar.value,
                    onDismissRequest = {
                        isMenuVisibleResTopBar.value = false
                    },
                    modifier = Modifier.background(BoxColor)
                ) {
                    imageResourcesResumeTopBar.forEachIndexed { index, (imageResource, name) ->
                        DropdownMenuItem(
                            onClick = {
                                isMenuVisibleResTopBar.value = false
                                isBoxVisibleResTopBar.value = true
                                // Store the selected dropdown menu item
                                selectedMenuItem = Pair(index, name)
                            },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(imageResource),
                                    contentScale = ContentScale.FillWidth,
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )
                            },
                            text = { Text(text = name, fontFamily = DateTittle, fontSize = 24.sp) }
                        )
                    }
                }
            }
            Box {
                DropdownMenu(
                    offset = DpOffset.Zero,
                    expanded = isBoxVisibleResTopBar.value,
                    onDismissRequest = {
                        isBoxVisibleResTopBar.value = false
                    },
                    modifier = Modifier.background(BoxColor),
                    content = {
                        val selectedList = selectedMenuItem?.let {
                            adjectivesWithColorsMap[it.second]
                        } ?: emptyList()

                        selectedList.forEach { (adjective, color) ->
                            Row(modifier = Modifier.padding(2.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .background(color)
                                        .border(
                                            border = BorderStroke(
                                                1.dp,
                                                Color.Black
                                            )
                                        )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = adjective,
                                    fontSize = 18.sp,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("Calendar") }, content = {
                Image(
                    painterResource(id = R.drawable.goback), contentDescription = null
                )
            })
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Year: $selectedYear",
                    fontSize = 34.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DateTittle
                )
            }
        }
    )
}
