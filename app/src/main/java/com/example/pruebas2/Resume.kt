package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.Purple40
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Resume(navController: NavHostController) {
    Scaffold(
        topBar = { MyResTopBar() },
        floatingActionButton = { },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding()
                )
            ) { MyResTabs(navController) }
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyResTabs(navController: NavHostController) {
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
            containerColor = Purple40,
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
fun MyResTopBar() {
    val currentDate = getCurrentDate()
    TopAppBar(modifier = Modifier.height(40.dp), navigationIcon = {
        IconButton(onClick = { /*TODO*/ }, content = {
            Image(
                painterResource(id = R.drawable.daywas), contentDescription = null
            )
        })
    },
        title = {
            Text(
                text = currentDate,
                fontSize = 12.sp,
                color = Color.Black,
            )
        })

}