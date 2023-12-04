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
import com.example.pruebas2.ui.theme.Purple40
import com.example.pruebas2.ui.theme.TabsColor
import com.example.pruebas2.ui.theme.TopBarColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Resume(navController: NavHostController) {
    Scaffold(
        topBar = { MyResTopBar(navController) },
        floatingActionButton = { },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding()
                    )
                    .background(BoxColor)
            ) { MyResTabs()}
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyResTabs() {
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

    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { 4 }
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
                0 -> ResumeDay()
                1 -> ""
                2 -> ""
                3 -> ""
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyResTopBar(navController: NavHostController) {
    TopAppBar(
        modifier = Modifier.height(40.dp),
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
            })
                         },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Year 202*",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    )
}

