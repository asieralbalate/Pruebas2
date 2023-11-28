package com.example.pruebas2

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
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


data class AdjectiveColorPair(val adjective: String, val color: Color)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Diary(selectedDate: String, navController: NavHostController) {
    val context = LocalContext.current
    var selectedDiaryAdjective by remember { mutableStateOf<Int? >(null) }
    var selectedWeatherAdjective by remember { mutableStateOf<Int?>(null) }
    var selectedStepsAdjective by remember { mutableStateOf<Int?>(null) }
    var selectedSpendAdjective by remember { mutableStateOf<Int?>(null) }
    var selectedWeightAdjective by remember { mutableStateOf<Int?>(null) }
    var selectedFoodAdjective by remember { mutableStateOf<Int?>(null) }
    var selectedSleepAdjective by remember { mutableStateOf<Int?>(null) }
    Scaffold(
        topBar = { MyTopBar(navController, selectedDate) },
        floatingActionButton = {
            MyFAB(
                selectedDate, selectedDiaryAdjective,
                selectedWeatherAdjective, selectedStepsAdjective,
                selectedSpendAdjective, selectedWeightAdjective,
                selectedFoodAdjective, selectedSleepAdjective,
                context
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding()
                )
            ) {
                MyTabs(
                    selectedDiaryAdjective,
                    { newDiaryAdjective ->
                        selectedDiaryAdjective = newDiaryAdjective
                    },
                    selectedWeatherAdjective,
                    { newWeatherAdjective ->
                        selectedWeatherAdjective = newWeatherAdjective
                    },
                    selectedStepsAdjective,
                    { newStepsAdjective ->
                        selectedStepsAdjective = newStepsAdjective
                    },
                    selectedSpendAdjective,
                    { newSpendAdjective ->
                        selectedSpendAdjective = newSpendAdjective
                    },
                    selectedWeightAdjective,
                    { newWeightAdjective ->
                        selectedWeightAdjective = newWeightAdjective
                    },
                    selectedFoodAdjective,
                    { newFoodAdjective ->
                        selectedFoodAdjective = newFoodAdjective
                    },
                    selectedSleepAdjective,
                    { newSleepAdjective ->
                        selectedSleepAdjective = newSleepAdjective
                    }
                )
            }
        })
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabs(
    selectedDiaryAdjective: Int?, onAdjectiveSelected: (Int?) -> Unit,
    selectedWeatherAdjective: Int?, onWeatherSelected: (Int?) -> Unit,
    selectedStepsAdjective: Int?, onStepsSelected: (Int?) -> Unit,
    selectedSpendAdjective: Int?, onSpendSelected: (Int?) -> Unit,
    selectedWeightAdjective: Int?, onWeightSelected: (Int?) -> Unit,
    selectedFoodAdjective: Int?, onFoodSelected: (Int?) -> Unit,
    selectedSleepAdjective: Int?, onSleepSelected: (Int?) -> Unit
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

    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { 7 }
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
                2 -> Steps(selectedStepsAdjective, onStepsSelected)
                3 -> Spend(selectedSpendAdjective, onSpendSelected)
                4 -> Weights(selectedWeightAdjective, onWeightSelected)
                5 -> Food(selectedFoodAdjective, onFoodSelected)
                6 -> Sleep(selectedSleepAdjective, onSleepSelected)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController, selectedDate: String) {
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
                    text = selectedDate,
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        })
}


@Composable
fun MyFAB(
    dateCal: String,
    selectedDiaryAdjective: Int?,
    selectedWeatherAdjective: Int?,
    selectedStepsAdjective: Int?,
    selectedSpendAdjective: Int?,
    selectedWeightAdjective: Int?,
    selectedFoodAdjective: Int?,
    selectedSleepAdjective: Int?,
    contexto: Context,
) {
    FloatingActionButton(onClick = {
        UploadData(
            dateCal = dateCal,
            selectedDiaryAdjective = selectedDiaryAdjective,
            selectedWeatherAdjective = selectedWeatherAdjective,
            selectedStepsAdjective = selectedStepsAdjective,
            selectedSpendAdjective = selectedSpendAdjective,
            selectedWeightAdjective = selectedWeightAdjective,
            selectedFoodAdjective = selectedFoodAdjective,
            selectedSleepAdjective = selectedSleepAdjective,
            contexto = contexto,
        )
    }, containerColor = Purple40) {
        Image(
            painterResource(id = R.drawable.save),
            contentDescription = "null",
            modifier = Modifier.size(60.dp)
        )
    }
}

fun UploadData(
    dateCal: String,
    selectedDiaryAdjective: Int?,
    selectedWeatherAdjective: Int?,
    selectedStepsAdjective: Int?,
    selectedSpendAdjective: Int?,
    selectedWeightAdjective: Int?,
    selectedFoodAdjective: Int?,
    selectedSleepAdjective: Int?,
    contexto: Context,
) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "https://dailyasiercalendar.000webhostapp.com/insertCalendar.php"
    val parametros = JSONObject()
    parametros.put("dateCal", dateCal)
    parametros.put("day", selectedDiaryAdjective)
    parametros.put("weather", selectedWeatherAdjective)
    parametros.put("steps", selectedStepsAdjective)
    parametros.put("spend", selectedSpendAdjective)
    parametros.put("weights", selectedWeightAdjective)
    parametros.put("food", selectedFoodAdjective)
    parametros.put("sleep", selectedSleepAdjective)
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

fun CheckDate(dateCal: String, existeFechaCallback: (Boolean) -> Unit, contexto: Context) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "https://dailyasiercalendar.000webhostapp.com/checkCalendar.php?dateCal=$dateCal"

    val requerimiento = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            // Si la respuesta contiene datos, significa que la fecha ya existe
            existeFechaCallback(response.length() > 0)
        },
        { error ->
            // Manejar errores según sea necesario
            existeFechaCallback(false)
        }
    )

    requestQueue.add(requerimiento)
}

@Composable
fun AdjectiveRow(
    adjective: String,
    color: Color,
    selectedDiaryAdjective: Int?,
    onAdjectiveSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 20.dp)
            .selectable(
                selected = (selectedDiaryAdjective == adjectivesWithColors.indexOfFirst { it.adjective == adjective }),
                onClick = {
                    onAdjectiveSelected(if (selectedDiaryAdjective == adjectivesWithColors.indexOfFirst { it.adjective == adjective }) {
                        null
                    } else {
                        adjectivesWithColors.indexOfFirst { it.adjective == adjective }
                    })
                }
            )
    ) {
        Checkbox(
            checked = (selectedDiaryAdjective == adjectivesWithColors.indexOfFirst { it.adjective == adjective }),
            onCheckedChange = null,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = adjective,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}