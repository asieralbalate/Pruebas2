package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.BoxColor
import com.example.pruebas2.ui.theme.DateTittle
import com.example.pruebas2.ui.theme.Purple40
import com.example.pruebas2.ui.theme.TabsColor
import com.example.pruebas2.ui.theme.TopBarColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Resume(selectedYear: String, navController: NavHostController) {
    val dataMap: Map<String, Int> = mapOf(
        "11-02-23" to 0,
        "12-03-23" to 1,
        "10-01-23" to 2,
        "20-01-23" to 3,
        "15-06-23" to 4,
        "15-08-23" to 5,
        "10-08-23" to 6,
        "12-12-23" to 7,
        "01-02-23" to 4,
        "05-03-23" to 2,
        "10-04-23" to 7,
        "15-06-23" to 1,
        "20-08-23" to 9,
        "22-09-23" to 3,
        "03-11-23" to 6,
        "08-12-23" to 0,
        "18-01-23" to 5,
        "25-03-23" to 8,
        "14-02-23" to 3,
        "08-05-23" to 6,
        "23-07-23" to 1,
        "05-09-23" to 7,
        "30-10-23" to 9,
        "12-11-23" to 0,
        "02-01-23" to 5,
        "17-03-23" to 2,
        "21-04-23" to 4,
        "26-06-23" to 8,
        "07-02-23" to 8,
        "18-04-23" to 1,
        "26-06-23" to 4,
        "05-08-23" to 2,
        "15-09-23" to 6,
        "22-10-23" to 3,
        "01-12-23" to 9,
        "11-01-23" to 5,
        "24-03-23" to 7,
        "30-05-23" to 0
    )
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
            ) { MyResTabs(dataMap)}
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyResTabs(dataMap: Map<String,Int>) {
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
    TopAppBar(
        modifier = Modifier.height(44.dp),
        colors = TopAppBarColors(
            containerColor = TopBarColor,
            scrolledContainerColor = Color.White,
            navigationIconContentColor = TopBarColor,
            titleContentColor = TopBarColor,
            actionIconContentColor = Color.White
        ), actions = { IconButton(onClick = { /*TODO*/ }) {
            Image(
                painterResource(id = R.drawable.actrionsresume), contentDescription = null
            )
        }},
        navigationIcon = {
            IconButton(onClick = { navController.navigate("Calendar") }, content = {
                Image(
                    painterResource(id = R.drawable.goback), contentDescription = null
                )
            }) },
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
