package com.example.pruebas2

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebas2.ui.theme.Purple40
import kotlinx.coroutines.launch
import org.json.JSONObject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Diary(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = { MyTopBar(navController) },
        floatingActionButton = { MyFAB(context) },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            var selectedDiaryAdjective by remember { mutableStateOf<Int?>(null) }
            var selectedWeatherAdjective by remember { mutableStateOf<String?>(null) }
            Box(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding()
                )
            ) {
                MyTabs(
                    navController,
                    selectedDiaryAdjective,
                    { newDiaryAdjective ->
                        selectedDiaryAdjective = newDiaryAdjective
                    },
                    selectedWeatherAdjective,
                    { newWeatherAdjective ->
                        selectedWeatherAdjective = newWeatherAdjective
                    }
                )
            }
        })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabs(
    navController: NavHostController,
    selectedDiaryAdjective: Int?, onAdjectiveSelected: (Int?) -> Unit,
    selectedWeatherAdjective: String?, onWeatherSelected: (String?) -> Unit
) {
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
                0 -> Day(selectedDiaryAdjective, onAdjectiveSelected)
                1 -> Weather(selectedWeatherAdjective, onWeatherSelected)
                2 -> Steps()
                3 -> ""
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController) {
    val currentDate = getCurrentDate()
    TopAppBar(modifier = Modifier.height(40.dp), colors = TopAppBarColors(
        containerColor = Purple40,
        scrolledContainerColor = Color.White,
        navigationIconContentColor = Purple40,
        titleContentColor = Purple40,
        actionIconContentColor = Color.White
    ), navigationIcon = {
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
                    text = currentDate,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        })

}


@Composable
fun MyFAB(context: Context) {
    FloatingActionButton(onClick = {
        UploadData(
        date = "hola",
        selectedDiaryAdjective = 1,
        contexto = context,
    ) }, containerColor = Purple40) {
        Image(
            painterResource(id = R.drawable.save),
            contentDescription = "null",
            modifier = Modifier.size(60.dp)
        )
    }
}

fun UploadData(date: String, selectedDiaryAdjective: Int? , contexto: Context) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "https://dailyasiercalendar.000webhostapp.com/insertar.php"
    val parametros= JSONObject()
    parametros.put("dates",date)
    parametros.put("day",selectedDiaryAdjective)
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        parametros,
        { response ->

        },
        { error ->

        }
    )
    requestQueue.add(requerimiento)
}

