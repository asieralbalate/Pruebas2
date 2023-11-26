package com.example.pruebas2

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebas2.ui.theme.Purple40
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Diary(navController: NavHostController) {
    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = { MyFAB() },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding()
                )
            ) { MyTabs(navController) }
        })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabs(navController: NavHostController) {
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
                0 -> Day()
                1 -> ""
                2 -> ""
                3 -> ""
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
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


@Composable
fun MyFAB() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Image(painterResource(id = R.drawable.daywas), contentDescription = "null")
    }
}
