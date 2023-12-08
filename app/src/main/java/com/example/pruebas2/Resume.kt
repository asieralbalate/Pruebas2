package com.example.pruebas2

import android.annotation.SuppressLint
import android.content.Context
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
    val imageResources = listOf(
        R.drawable.daywas,
        R.drawable.weather,
        R.drawable.steps,
        R.drawable.spend,
        R.drawable.weights,
        R.drawable.food,
        R.drawable.sleep,
    )

    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { 7 }
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = TabsColor,
            contentColor = Color.White,

            ) {
            imageResources.forEachIndexed { index, imageResource ->
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
    var isMenuVisible by remember { mutableStateOf(false) }
    var isBoxVisible by remember { mutableStateOf(false) }
    var selectedMenuItem by remember { mutableStateOf<Pair<Int, String>?>(null) }
    val imageResources = listOf(
        Pair(R.drawable.daywas, "Mood"),
        Pair(R.drawable.weather, "Weather"),
        Pair(R.drawable.steps, "Steps"),
        Pair(R.drawable.spend, "Costs"),
        Pair(R.drawable.weights, "Sports"),
        Pair(R.drawable.food, "Foods"),
        Pair(R.drawable.sleep, "Sleep"),
    )

    val adjectivesWithColorsMood = listOf(
        Pair("Fantastic", DiaryColor0),
        Pair("Terrible", DiaryColor1),
        Pair("Productive", DiaryColor2),
        Pair("Challenging", DiaryColor3),
        Pair("Relaxing", DiaryColor4),
        Pair("Exciting", DiaryColor5),
        Pair("Hectic", DiaryColor6),
        Pair("Joyful", DiaryColor7),
        Pair("Frustrating", DiaryColor8),
        Pair("Rewarding", DiaryColor9)
    )

    val adjectivesWithColorsFoods = listOf(
        Pair("Italian", FoodColor0),
        Pair("American", FoodColor1),
        Pair("Turkish", FoodColor2),
        Pair("Mexican", FoodColor3),
        Pair("Japanese", FoodColor4),
        Pair("Indian", FoodColor5),
        Pair("Mediterranean", FoodColor6),
        Pair("Chinese", FoodColor7),
        Pair("Thai", FoodColor8),
        Pair("French", FoodColor9)
    )

    val adjectivesWithColorsSleep = listOf(
        Pair("-4 hours", SleepColor0),
        Pair("4 hours", SleepColor1),
        Pair("5 hours", SleepColor2),
        Pair("6 hours", SleepColor3),
        Pair("7 hours", SleepColor4),
        Pair("8 hours", SleepColor5),
        Pair("9 hours", SleepColor6),
        Pair("10 hours", SleepColor7),
        Pair("11 hours", SleepColor8),
        Pair("+11 hours", SleepColor9)
    )

    val adjectivesWithColorsCosts = listOf(
        Pair("-1€", SpendColor0),
        Pair("1 - 5 €", SpendColor1),
        Pair("5 - 10 €", SpendColor2),
        Pair("10 - 20 €", SpendColor3),
        Pair("20 - 50 €", SpendColor4),
        Pair("50 - 100 €", SpendColor5),
        Pair("100 - 200 €", SpendColor6),
        Pair("200 - 500 €", SpendColor7),
        Pair("500 - 1.000 €", SpendColor8),
        Pair("+1.000 €", SpendColor9)
    )

    val adjectivesWithColorsSteps = listOf(
        Pair("-100", StepsColor0),
        Pair("100 - 500", StepsColor1),
        Pair("500 - 1k", StepsColor2),
        Pair("1k - 2k", StepsColor3),
        Pair("2k - 5k", StepsColor4),
        Pair("5k - 10k", StepsColor5),
        Pair("10k - 15k", StepsColor6),
        Pair("15k - 20k", StepsColor7),
        Pair("20k - 30k", StepsColor8),
        Pair("+30k", StepsColor9)
    )

    val adjectivesWithColorsWeather = listOf(
        Pair("Sunny", WeatherColor0),
        Pair("Rainy", WeatherColor1),
        Pair("Cloudy", WeatherColor2),
        Pair("Windy", WeatherColor3),
        Pair("Snowy", WeatherColor4),
        Pair("Stormy", WeatherColor5),
        Pair("Foggy", WeatherColor6),
        Pair("Clear", WeatherColor7),
        Pair("Warm", WeatherColor8),
        Pair("Cold", WeatherColor9)
    )

    val adjectivesWithColorsSports = listOf(
        Pair("Running", WeightsColor0),
        Pair("Climbing", WeightsColor1),
        Pair("Weightlifting", WeightsColor2),
        Pair("Cycling", WeightsColor3),
        Pair("Swimming", WeightsColor4),
        Pair("Boxing", WeightsColor5),
        Pair("Yoga", WeightsColor6),
        Pair("Dancing", WeightsColor7),
        Pair("Martial Arts", WeightsColor8),
        Pair("Gymnastics", WeightsColor9)
    )
    val adjectivesWithColorsMap = mapOf(
        imageResources[0].second to adjectivesWithColorsMood,
        imageResources[1].second to adjectivesWithColorsWeather,
        imageResources[2].second to adjectivesWithColorsSteps,
        imageResources[3].second to adjectivesWithColorsCosts,
        imageResources[4].second to adjectivesWithColorsSports,
        imageResources[5].second to adjectivesWithColorsFoods,
        imageResources[6].second to adjectivesWithColorsSleep
    )

    TopAppBar(
        modifier = Modifier.height(44.dp),
        colors = TopAppBarColors(
            containerColor = TopBarColor,
            scrolledContainerColor = Color.White,
            navigationIconContentColor = TopBarColor,
            titleContentColor = TopBarColor,
            actionIconContentColor = Color.White
        ), actions = {
            IconButton(onClick = { isMenuVisible = true }) {
                Image(
                    painterResource(id = R.drawable.actionsresume), contentDescription = null
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DropdownMenu(
                    expanded = isMenuVisible,
                    onDismissRequest = {
                        isMenuVisible = false
                    },
                    modifier = Modifier.background(BoxColor)
                ) {
                    imageResources.forEachIndexed { index, (imageResource, name) ->
                        DropdownMenuItem(
                            onClick = {
                                isMenuVisible = false
                                isBoxVisible = true
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
                    expanded = isBoxVisible,
                    onDismissRequest = {
                        isBoxVisible = false
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
