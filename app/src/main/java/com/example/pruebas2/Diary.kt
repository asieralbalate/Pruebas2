package com.example.pruebas2

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pruebas2.ui.theme.BoxColor
import com.example.pruebas2.ui.theme.DateTittle
import com.example.pruebas2.ui.theme.DiaryColor0
import com.example.pruebas2.ui.theme.DiaryColor1
import com.example.pruebas2.ui.theme.DiaryColor2
import com.example.pruebas2.ui.theme.DiaryColor3
import com.example.pruebas2.ui.theme.DiaryColor4
import com.example.pruebas2.ui.theme.DiaryColor5
import com.example.pruebas2.ui.theme.DiaryColor6
import com.example.pruebas2.ui.theme.DiaryColor7
import com.example.pruebas2.ui.theme.DiaryColor8
import com.example.pruebas2.ui.theme.DiaryColor9
import com.example.pruebas2.ui.theme.FoodColor0
import com.example.pruebas2.ui.theme.FoodColor1
import com.example.pruebas2.ui.theme.FoodColor2
import com.example.pruebas2.ui.theme.FoodColor3
import com.example.pruebas2.ui.theme.FoodColor4
import com.example.pruebas2.ui.theme.FoodColor5
import com.example.pruebas2.ui.theme.FoodColor6
import com.example.pruebas2.ui.theme.FoodColor7
import com.example.pruebas2.ui.theme.FoodColor8
import com.example.pruebas2.ui.theme.FoodColor9
import com.example.pruebas2.ui.theme.SleepColor0
import com.example.pruebas2.ui.theme.SleepColor1
import com.example.pruebas2.ui.theme.SleepColor2
import com.example.pruebas2.ui.theme.SleepColor3
import com.example.pruebas2.ui.theme.SleepColor4
import com.example.pruebas2.ui.theme.SleepColor5
import com.example.pruebas2.ui.theme.SleepColor6
import com.example.pruebas2.ui.theme.SleepColor7
import com.example.pruebas2.ui.theme.SleepColor8
import com.example.pruebas2.ui.theme.SleepColor9
import com.example.pruebas2.ui.theme.SpendColor0
import com.example.pruebas2.ui.theme.SpendColor1
import com.example.pruebas2.ui.theme.SpendColor2
import com.example.pruebas2.ui.theme.SpendColor3
import com.example.pruebas2.ui.theme.SpendColor4
import com.example.pruebas2.ui.theme.SpendColor5
import com.example.pruebas2.ui.theme.SpendColor6
import com.example.pruebas2.ui.theme.SpendColor7
import com.example.pruebas2.ui.theme.SpendColor8
import com.example.pruebas2.ui.theme.SpendColor9
import com.example.pruebas2.ui.theme.StepsColor0
import com.example.pruebas2.ui.theme.StepsColor1
import com.example.pruebas2.ui.theme.StepsColor2
import com.example.pruebas2.ui.theme.StepsColor3
import com.example.pruebas2.ui.theme.StepsColor4
import com.example.pruebas2.ui.theme.StepsColor5
import com.example.pruebas2.ui.theme.StepsColor6
import com.example.pruebas2.ui.theme.StepsColor7
import com.example.pruebas2.ui.theme.StepsColor8
import com.example.pruebas2.ui.theme.StepsColor9
import com.example.pruebas2.ui.theme.TabsColor
import com.example.pruebas2.ui.theme.TopBarColor
import com.example.pruebas2.ui.theme.WeatherColor0
import com.example.pruebas2.ui.theme.WeatherColor1
import com.example.pruebas2.ui.theme.WeatherColor2
import com.example.pruebas2.ui.theme.WeatherColor3
import com.example.pruebas2.ui.theme.WeatherColor4
import com.example.pruebas2.ui.theme.WeatherColor5
import com.example.pruebas2.ui.theme.WeatherColor6
import com.example.pruebas2.ui.theme.WeatherColor7
import com.example.pruebas2.ui.theme.WeatherColor8
import com.example.pruebas2.ui.theme.WeatherColor9
import com.example.pruebas2.ui.theme.WeightsColor0
import com.example.pruebas2.ui.theme.WeightsColor1
import com.example.pruebas2.ui.theme.WeightsColor2
import com.example.pruebas2.ui.theme.WeightsColor3
import com.example.pruebas2.ui.theme.WeightsColor4
import com.example.pruebas2.ui.theme.WeightsColor5
import com.example.pruebas2.ui.theme.WeightsColor6
import com.example.pruebas2.ui.theme.WeightsColor7
import com.example.pruebas2.ui.theme.WeightsColor8
import com.example.pruebas2.ui.theme.WeightsColor9
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale


data class AdjectiveColorPair(val adjective: String, val color: Color, val image: Int)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Diary(selectedDate: String, navController: NavHostController) {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = inputFormat.parse(selectedDate)
    val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    // Format the date in the desired format
    val formattedDate = formatDate(localDate)
    val context = LocalContext.current

    var selectedDiaryAdjective by remember { mutableStateOf<Int?>(-1) }
    var selectedWeatherAdjective by remember { mutableStateOf<Int?>(-1) }
    var selectedStepsAdjective by remember { mutableStateOf<Int?>(-1) }
    var selectedSpendAdjective by remember { mutableStateOf<Int?>(-1) }
    var selectedWeightAdjective by remember { mutableStateOf<Int?>(-1) }
    var selectedFoodAdjective by remember { mutableStateOf<Int?>(-1) }
    var selectedSleepAdjective by remember { mutableStateOf<Int?>(-1) }
    Scaffold(
        topBar = { MyTopBar(navController, formattedDate) },
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
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding()
                    )
                    .background(BoxColor)
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
            containerColor = TabsColor,
            contentColor = Color.White
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
    TopAppBar(modifier = Modifier.height(44.dp), colors = TopAppBarColors(
        containerColor = TopBarColor,
        scrolledContainerColor = Color.White,
        navigationIconContentColor =TopBarColor,
        titleContentColor = TopBarColor,
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
                    fontSize = 34.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DateTittle
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
    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000,
                easing = LinearEasing
            )
        )
    )
    val colors = listOf(
        DiaryColor0, DiaryColor1, DiaryColor2, DiaryColor3,
        DiaryColor4, DiaryColor5, DiaryColor6, DiaryColor7, DiaryColor8,
        DiaryColor9, FoodColor0, FoodColor1, FoodColor2, FoodColor3,
        FoodColor4, FoodColor5, FoodColor6, FoodColor7, FoodColor8,
        FoodColor9,  SleepColor0, SleepColor4, SleepColor1, SleepColor2, SleepColor3,
        SleepColor5, SleepColor8, SleepColor9,  SleepColor6, SleepColor7,
        SpendColor0, SpendColor1, SpendColor2, SpendColor3,
        SpendColor4, SpendColor5, SpendColor6, SpendColor8,
        SpendColor9, SpendColor7, StepsColor0, StepsColor1, StepsColor2, StepsColor3,
        StepsColor4, StepsColor5, StepsColor6, StepsColor7, StepsColor8,
        StepsColor9, WeatherColor0, WeatherColor1, WeatherColor2, WeatherColor3,
        WeatherColor4, WeatherColor5, WeatherColor6, WeatherColor7, WeatherColor8,
        WeatherColor9, WeightsColor0, WeightsColor1, WeightsColor2, WeightsColor3,
        WeightsColor4, WeightsColor5, WeightsColor6, WeightsColor7, WeightsColor8,
        WeightsColor9
    )

    var gradientBrush by remember {
        mutableStateOf(
            Brush.horizontalGradient(
                colors = colors,
                startX = -10.0f,
                endX = 400.0f,
                tileMode = TileMode.Repeated
            )
        )
    }
    Box (modifier = Modifier
        .drawBehind {
            rotate(value) {
                drawCircle(
                    gradientBrush, style = Stroke(width = 20.dp.value)
                )
            }
        }.size(60.dp)){
        FloatingActionButton(onClick = {
            InsertData(
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

        },
            containerColor = TopBarColor,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier.size(60.dp),
            shape = CircleShape
        ) {
            Image(
                painterResource(id = R.drawable.save),
                contentDescription = "null",
                modifier = Modifier.size(80.dp).scale(1.3f),
                contentScale = ContentScale.Crop
            )
        }
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
    CheckDate(dateCal,
        {
        exists ->
            if (exists) {
                UpdateData(dateCal,
                    selectedDiaryAdjective,
                    selectedWeatherAdjective,
                    selectedStepsAdjective,
                    selectedSpendAdjective,
                    selectedWeightAdjective,
                    selectedFoodAdjective,
                    selectedSleepAdjective,
                    contexto)
            } else {
                InsertData(dateCal,
                    selectedDiaryAdjective,
                    selectedWeatherAdjective,
                    selectedStepsAdjective,
                    selectedSpendAdjective,
                    selectedWeightAdjective,
                    selectedFoodAdjective,
                    selectedSleepAdjective,
                contexto)
            }
        },
        contexto)
}

fun CheckDate(dateCal: String, existeFechaCallback: (Boolean) -> Unit, contexto: Context) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "https://dailyasiercalendar.000webhostapp.com/checkCalendar.php?dateCal=$dateCal"
    val requerimiento = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    existeFechaCallback(true)
                } catch (e: JSONException) {
                }
            } else {
                existeFechaCallback(false)
            }
        }
    ) { error ->
    }
    requestQueue.add(requerimiento)
}

fun InsertData(
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

fun UpdateData(
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
    val url = "https://dailyasiercalendar.000webhostapp.com/updateCalendar.php"
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
        Request.Method.PUT,
        url,
        parametros,
        { response ->
        },
        { error ->
        }
    )
    requestQueue.add(requerimiento)
}


@Composable
fun AdjectiveRow(
    adjective: String,
    color: Color,
    image: Int,
    selectedDiaryAdjective: Int?,
    onAdjectiveSelected: (Int?) -> Unit,
    adjectivesWithColors: List<AdjectiveColorPair>
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp, start = 18.dp)
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
            modifier = Modifier.size(30.dp),
            colors = CheckboxDefaults.colors(checkedColor = TopBarColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(color)
                .border(border = BorderStroke(1.dp, Color.Black))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(57.dp)
        ){
            Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = adjective,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun formatDate(date: LocalDate): String {
    val dayOfMonth = date.dayOfMonth
    val month = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = date.year

    val dayWithExtension = when {
        dayOfMonth in 11..13 -> "${dayOfMonth}th"
        dayOfMonth % 10 == 1 -> "${dayOfMonth}st"
        dayOfMonth % 10 == 2 -> "${dayOfMonth}nd"
        dayOfMonth % 10 == 3 -> "${dayOfMonth}rd"
        else -> "${dayOfMonth}th"
    }
    return "$dayWithExtension of $month $year"
}