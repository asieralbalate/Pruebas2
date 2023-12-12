package com.example.pruebas2

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

//---------calendar-------------//
val eventsData = mutableStateListOf<Event>()
data class Event(val dateCal: String, val event: String)
var showDialogEvents = mutableStateOf(false)
var showDialogDiary = mutableStateOf(false)
var showDropDownMenu = mutableStateOf(false)

//-----------Diary-------------//
data class AdjectiveColorPair(val adjective: String, val color: Color, val image: Int)

var selectedDiaryAdjective = mutableStateOf<Int?>(-1)
var selectedWeatherAdjective = mutableStateOf<Int?>(-1)
var selectedStepsAdjective = mutableStateOf<Int?>(-1)
var selectedSpendAdjective = mutableStateOf<Int?>(-1)
var selectedWeightAdjective = mutableStateOf<Int?>(-1)
var selectedFoodAdjective = mutableStateOf<Int?>(-1)
var selectedSleepAdjective = mutableStateOf<Int?>(-1)

val adjectivesWithColorsDay = listOf(
    AdjectiveColorPair("Fantastic", DiaryColor0, R.drawable.fantastic),
    AdjectiveColorPair("Terrible", DiaryColor1, R.drawable.terrible),
    AdjectiveColorPair("Productive", DiaryColor2, R.drawable.productive),
    AdjectiveColorPair("Challenging", DiaryColor3, R.drawable.challenging),
    AdjectiveColorPair("Relaxing", DiaryColor4, R.drawable.relaxing),
    AdjectiveColorPair("Exciting", DiaryColor5, R.drawable.exciting),
    AdjectiveColorPair("Hectic", DiaryColor6, R.drawable.hectic),
    AdjectiveColorPair("Joyful", DiaryColor7, R.drawable.joyful),
    AdjectiveColorPair("Frustrating", DiaryColor8, R.drawable.frustrating),
    AdjectiveColorPair("Rewarding", DiaryColor9, R.drawable.rewarding)
)
val brushDay = Brush.linearGradient(listOf(
    DiaryColor0, DiaryColor1, DiaryColor2, DiaryColor3,
    DiaryColor4, DiaryColor5, DiaryColor6, DiaryColor7, DiaryColor8,
    DiaryColor9))

val adjectivesWithColorsFood = listOf(
    AdjectiveColorPair("Italian", FoodColor0, R.drawable.italian),
    AdjectiveColorPair("American", FoodColor1, R.drawable.american),
    AdjectiveColorPair("Turkish", FoodColor2, R.drawable.turkish),
    AdjectiveColorPair("Mexican", FoodColor3, R.drawable.mexican),
    AdjectiveColorPair("Japanese", FoodColor4, R.drawable.japanese),
    AdjectiveColorPair("Indian", FoodColor5, R.drawable.indian),
    AdjectiveColorPair("Mediterranean", FoodColor6, R.drawable.mediterranean),
    AdjectiveColorPair("Chinese", FoodColor7, R.drawable.chinese),
    AdjectiveColorPair("Thai", FoodColor8, R.drawable.thay),
    AdjectiveColorPair("French", FoodColor9, R.drawable.french)
)
val brushFood = Brush.linearGradient(listOf(
    FoodColor0, FoodColor1, FoodColor2, FoodColor3,
    FoodColor4, FoodColor5, FoodColor6, FoodColor7, FoodColor8,
    FoodColor9
))

val adjectivesWithColorsSleep = listOf(
    AdjectiveColorPair("-4 hours", SleepColor0, R.drawable.lessthreeh),
    AdjectiveColorPair("4 hours", SleepColor1, R.drawable.fourh),
    AdjectiveColorPair("5 hours", SleepColor2, R.drawable.fiveh),
    AdjectiveColorPair("6 hours", SleepColor3, R.drawable.sixh),
    AdjectiveColorPair("7 hours", SleepColor4, R.drawable.sevenh),
    AdjectiveColorPair("8 hours", SleepColor5, R.drawable.eighth),
    AdjectiveColorPair("9 hours", SleepColor6, R.drawable.nineh),
    AdjectiveColorPair("10 hours", SleepColor7, R.drawable.tenh),
    AdjectiveColorPair("11 hours", SleepColor8, R.drawable.elevenh),
    AdjectiveColorPair("+11 hours", SleepColor9, R.drawable.morelevenh)
)
val brushSleep = Brush.linearGradient(listOf(
    SleepColor0, SleepColor4,SleepColor1, SleepColor2, SleepColor3,
    SleepColor5, SleepColor8,
    SleepColor9,  SleepColor6,SleepColor7
))

val adjectivesWithColorsSpend = listOf(
    AdjectiveColorPair("-1€", SpendColor0, R.drawable.one),
    AdjectiveColorPair("1 - 5 €", SpendColor1,R.drawable.five),
    AdjectiveColorPair("5 - 10 €", SpendColor2, R.drawable.ten),
    AdjectiveColorPair("10 - 20 €", SpendColor3, R.drawable.twenty),
    AdjectiveColorPair("20 - 50 €", SpendColor4, R.drawable.fifty),
    AdjectiveColorPair("50 - 100 €", SpendColor5, R.drawable.hundredk),
    AdjectiveColorPair("100 - 200 €", SpendColor6, R.drawable.twohundk),
    AdjectiveColorPair("200 - 500 €", SpendColor7, R.drawable.fivehundk),
    AdjectiveColorPair("500 - 1.000 €", SpendColor8, R.drawable.thousk),
    AdjectiveColorPair("+1.000 €", SpendColor9, R.drawable.morethousk)
)
val brushSpend = Brush.linearGradient(listOf(
    SpendColor0, SpendColor1, SpendColor2, SpendColor3,
    SpendColor4, SpendColor5, SpendColor6, SpendColor8,
    SpendColor9, SpendColor7
))

val adjectivesWithColorsSteps = listOf(
    AdjectiveColorPair("-100", StepsColor0, R.drawable.lesshundred),
    AdjectiveColorPair("100 - 500", StepsColor1, R.drawable.fivehundred),
    AdjectiveColorPair("500 - 1k", StepsColor2, R.drawable.thousand),
    AdjectiveColorPair("1k - 2k", StepsColor3, R.drawable.twothous),
    AdjectiveColorPair("2k - 5k", StepsColor4, R.drawable.fivethous),
    AdjectiveColorPair("5k - 10k", StepsColor5, R.drawable.tenthous),
    AdjectiveColorPair("10k - 15k", StepsColor6, R.drawable.fifteenthous),
    AdjectiveColorPair("15k - 20k", StepsColor7, R.drawable.twentythous),
    AdjectiveColorPair("20k - 30k", StepsColor8, R.drawable.thirtythous),
    AdjectiveColorPair("+30k", StepsColor9, R.drawable.morethirtythous)
)
val brushSteps = Brush.linearGradient(listOf(
    StepsColor0, StepsColor1, StepsColor2, StepsColor3,
    StepsColor4, StepsColor5, StepsColor6, StepsColor7, StepsColor8,
    StepsColor9
))

val adjectivesWithColorsWeather = listOf(
    AdjectiveColorPair("Sunny", WeatherColor0, R.drawable.sunny),
    AdjectiveColorPair("Rainy", WeatherColor1, R.drawable.rainy),
    AdjectiveColorPair("Cloudy", WeatherColor2, R.drawable.cloudy),
    AdjectiveColorPair("Windy", WeatherColor3, R.drawable.windy),
    AdjectiveColorPair("Snowy", WeatherColor4, R.drawable.snowy),
    AdjectiveColorPair("Stormy", WeatherColor5, R.drawable.stormy),
    AdjectiveColorPair("Foggy", WeatherColor6, R.drawable.foggy),
    AdjectiveColorPair("Clear", WeatherColor7, R.drawable.clear),
    AdjectiveColorPair("Warm", WeatherColor8, R.drawable.warm),
    AdjectiveColorPair("Cold", WeatherColor9, R.drawable.cold)
)
val brushWeather = Brush.linearGradient(listOf(
    WeatherColor0, WeatherColor1, WeatherColor2, WeatherColor3,
    WeatherColor4, WeatherColor5, WeatherColor6, WeatherColor7, WeatherColor8,
    WeatherColor9
))

val adjectivesWithColorsWeights = listOf(
    AdjectiveColorPair("Running", WeightsColor0, R.drawable.running),
    AdjectiveColorPair("Climbing", WeightsColor1, R.drawable.climbing),
    AdjectiveColorPair("Weightlifting", WeightsColor2, R.drawable.wheightlifting),
    AdjectiveColorPair("Cycling", WeightsColor3, R.drawable.cycling),
    AdjectiveColorPair("Swimming", WeightsColor4, R.drawable.swimming),
    AdjectiveColorPair("Boxing", WeightsColor5, R.drawable.boxing),
    AdjectiveColorPair("Yoga", WeightsColor6, R.drawable.yoga),
    AdjectiveColorPair("Dancing", WeightsColor7, R.drawable.dancing),
    AdjectiveColorPair("Martial Arts", WeightsColor8, R.drawable.martialarts),
    AdjectiveColorPair("Gymnastics", WeightsColor9, R.drawable.gymnastics)
)
val brushWeight = Brush.linearGradient(listOf(
    WeightsColor0, WeightsColor1, WeightsColor2, WeightsColor3,
    WeightsColor4, WeightsColor5, WeightsColor6, WeightsColor7, WeightsColor8,
    WeightsColor9
))

//-------------Resume-----------//
val imageResourcesScheduleTabs = listOf(
    R.drawable.daywas,
    R.drawable.weather,
    R.drawable.steps,
    R.drawable.spend,
    R.drawable.weights,
    R.drawable.food,
    R.drawable.sleep,
)
val imageResourcesResumeTopBar = listOf(
    Pair(R.drawable.daywas, "Mood"),
    Pair(R.drawable.weather, "Weather"),
    Pair(R.drawable.steps, "Steps"),
    Pair(R.drawable.spend, "Costs"),
    Pair(R.drawable.weights, "Sports"),
    Pair(R.drawable.food, "Foods"),
    Pair(R.drawable.sleep, "Sleep"),
)

val adjectivesWithColorsMoodResume = listOf(
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

val adjectivesWithColorsFoodsResume = listOf(
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

val adjectivesWithColorsSleepResume = listOf(
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

val adjectivesWithColorsCostsResume = listOf(
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

val adjectivesWithColorsStepsResume = listOf(
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

val adjectivesWithColorsWeatherResume = listOf(
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

val adjectivesWithColorsSportsResume = listOf(
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
    imageResourcesResumeTopBar[0].second to adjectivesWithColorsMoodResume,
    imageResourcesResumeTopBar[1].second to adjectivesWithColorsWeatherResume,
    imageResourcesResumeTopBar[2].second to adjectivesWithColorsStepsResume,
    imageResourcesResumeTopBar[3].second to adjectivesWithColorsCostsResume,
    imageResourcesResumeTopBar[4].second to adjectivesWithColorsSportsResume,
    imageResourcesResumeTopBar[5].second to adjectivesWithColorsFoodsResume,
    imageResourcesResumeTopBar[6].second to adjectivesWithColorsSleepResume
)

var isMenuVisibleResTopBar = mutableStateOf(false)
var isBoxVisibleResTopBar = mutableStateOf(false)

//-------------------ResumeTabsContent------------------//
fun getColorDay(dataMap: MutableMap<String, IntArray>, dayRow: Int, dayCol: Int): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[0]
            break
        }
    }
    return when {
        value == 0 -> DiaryColor0
        value == 1 -> DiaryColor1
        value == 2 -> DiaryColor2
        value == 3 -> DiaryColor3
        value == 4 -> DiaryColor4
        value == 5 -> DiaryColor5
        value == 6 -> DiaryColor6
        value == 7 -> DiaryColor7
        value == 8 -> DiaryColor8
        value == 9 -> DiaryColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

fun getColorFood(dataMap: MutableMap<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1 && dayCol == col) {
            value = m.value[5]
            break
        }
    }
    return when {
        value == 0 -> FoodColor0
        value == 1 -> FoodColor1
        value == 2 -> FoodColor2
        value == 3 -> FoodColor3
        value == 4 -> FoodColor4
        value == 5 -> FoodColor5
        value == 6 -> FoodColor6
        value == 7 -> FoodColor7
        value == 8 -> FoodColor8
        value == 9 -> FoodColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

fun getColorSleep(dataMap: MutableMap<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[6]
            break
        }
    }
    return when {
        value == 0 -> SleepColor0
        value == 1 -> SleepColor1
        value == 2 -> SleepColor2
        value == 3 -> SleepColor3
        value == 4 -> SleepColor4
        value == 5 -> SleepColor5
        value == 6 -> SleepColor6
        value == 7 -> SleepColor7
        value == 8 -> SleepColor8
        value == 9 -> SleepColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

fun getColorSpend(dataMap: MutableMap<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[3]
            break
        }
    }
    return when {
        value == 0 -> SpendColor0
        value == 1 -> SpendColor1
        value == 2 -> SpendColor2
        value == 3 -> SpendColor3
        value == 4 -> SpendColor4
        value == 5 -> SpendColor5
        value == 6 -> SpendColor6
        value == 7 -> SpendColor7
        value == 8 -> SpendColor8
        value == 9 -> SpendColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

fun getColorSteps(dataMap: MutableMap<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[2]
            break
        }
    }
    return when {
        value == 0 -> StepsColor0
        value == 1 -> StepsColor1
        value == 2 -> StepsColor2
        value == 3 -> StepsColor3
        value == 4 -> StepsColor4
        value == 5 -> StepsColor5
        value == 6 -> StepsColor6
        value == 7 -> StepsColor7
        value == 8 -> StepsColor8
        value == 9 -> StepsColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

fun getColorWeather(dataMap: MutableMap<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[1]
            break
        }
    }
    return when {
        value == 0 -> WeatherColor0
        value == 1 -> WeatherColor1
        value == 2 -> WeatherColor2
        value == 3 -> WeatherColor3
        value == 4 -> WeatherColor4
        value == 5 -> WeatherColor5
        value == 6 -> WeatherColor6
        value == 7 -> WeatherColor7
        value == 8 -> WeatherColor8
        value == 9 -> WeatherColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

fun getColorWeights(dataMap: Map<String, IntArray>, dayRow: Int, dayCol: Int ): Color{
    var value = -1
    for (m in dataMap){
        val row = m.key.split("-")[0].toInt()
        val col = m.key.split("-")[1].toInt()
        if (dayRow  == row - 1&& dayCol == col) {
            value = m.value[4]
            break
        }
    }
    return when {
        value == 0 -> WeightsColor0
        value == 1 -> WeightsColor1
        value == 2 -> WeightsColor2
        value == 3 -> WeightsColor3
        value == 4 -> WeightsColor4
        value == 5 -> WeightsColor5
        value == 6 -> WeightsColor6
        value == 7 -> WeightsColor7
        value == 8 -> WeightsColor8
        value == 9 -> WeightsColor9
        dayRow == 30 && dayCol == 2 -> Color.Black
        dayRow == 29 && dayCol == 2 -> Color.Black
        dayRow == 30 && dayCol == 4 -> Color.Black
        dayRow == 30 && dayCol == 6 -> Color.Black
        dayRow == 30 && dayCol == 9 -> Color.Black
        dayRow == 30 && dayCol == 11 -> Color.Black
        else -> {Color.Gray}
    }
}

//-----------------------------------------------//