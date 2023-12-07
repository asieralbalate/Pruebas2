package com.example.pruebas2

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import kotlin.system.exitProcess

fun uploadData(
    dateCal: String,
    selectedDiaryAdjective: Int?,
    selectedWeatherAdjective: Int?,
    selectedStepsAdjective: Int?,
    selectedSpendAdjective: Int?,
    selectedWeightAdjective: Int?,
    selectedFoodAdjective: Int?,
    selectedSleepAdjective: Int?,
    context: Context,
) {
    checkDate(dateCal,
        {
                exists ->
            if (exists) {
                updateData(dateCal,
                    selectedDiaryAdjective,
                    selectedWeatherAdjective,
                    selectedStepsAdjective,
                    selectedSpendAdjective,
                    selectedWeightAdjective,
                    selectedFoodAdjective,
                    selectedSleepAdjective,
                    context)
            } else {
                insertData(dateCal,
                    selectedDiaryAdjective,
                    selectedWeatherAdjective,
                    selectedStepsAdjective,
                    selectedSpendAdjective,
                    selectedWeightAdjective,
                    selectedFoodAdjective,
                    selectedSleepAdjective,
                    context)
            }
        },
        context)
}

fun checkDate(dateCal: String, existsDateCallback: (Boolean) -> Unit, context: Context) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/checkCalendar.php?dateCal=$dateCal"
    val req = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    existsDateCallback(true)
                } catch (e: JSONException) {
                    exitProcess(1)
                }
            } else {
                existsDateCallback(false)
            }
        }
    ) { error ->
    }
    requestQueue.add(req)
}

fun insertData(
    dateCal: String,
    selectedDiaryAdjective: Int?,
    selectedWeatherAdjective: Int?,
    selectedStepsAdjective: Int?,
    selectedSpendAdjective: Int?,
    selectedWeightAdjective: Int?,
    selectedFoodAdjective: Int?,
    selectedSleepAdjective: Int?,
    context: Context,
) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/insertCalendar.php"
    val parameters = JSONObject()
    parameters.put("dateCal", dateCal)
    parameters.put("day", selectedDiaryAdjective)
    parameters.put("weather", selectedWeatherAdjective)
    parameters.put("steps", selectedStepsAdjective)
    parameters.put("spend", selectedSpendAdjective)
    parameters.put("weights", selectedWeightAdjective)
    parameters.put("food", selectedFoodAdjective)
    parameters.put("sleep", selectedSleepAdjective)
    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        parameters,
        { response ->

        },
        { error ->

        }
    )
    requestQueue.add(req)
}

fun updateData(
    dateCal: String,
    selectedDiaryAdjective: Int?,
    selectedWeatherAdjective: Int?,
    selectedStepsAdjective: Int?,
    selectedSpendAdjective: Int?,
    selectedWeightAdjective: Int?,
    selectedFoodAdjective: Int?,
    selectedSleepAdjective: Int?,
    context: Context,
) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/updateCalendar.php"
    val parameters = JSONObject()
    parameters.put("dateCal", dateCal)
    parameters.put("day", selectedDiaryAdjective)
    parameters.put("weather", selectedWeatherAdjective)
    parameters.put("steps", selectedStepsAdjective)
    parameters.put("spend", selectedSpendAdjective)
    parameters.put("weights", selectedWeightAdjective)
    parameters.put("food", selectedFoodAdjective)
    parameters.put("sleep", selectedSleepAdjective)
    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        parameters,
        { response ->
        },
        { error ->
        }
    )
    requestQueue.add(req)
}

fun checkDatabase(context: Context): MutableMap<String, IntArray> {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/checkDatabase.php"
    val dataMap: MutableMap<String, IntArray> = mutableMapOf()

    val req = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            for (i in 0 until response.length()) {
                try {
                    val data = response.getJSONObject(i)
                    val dateCal = data.getString("dateCal")
                    val values = intArrayOf(
                        data.getInt("day"),
                        data.getInt("weather"),
                        data.getInt("steps"),
                        data.getInt("spend"),
                        data.getInt("weights"),
                        data.getInt("food"),
                        data.getInt("sleep")
                    )
                    dataMap[dateCal] = values
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
    ) { error ->
        error.printStackTrace()
    }
    requestQueue.add(req)
    return dataMap
}

fun listEvents(context: Context) {
    val url = "https://dailyasiercalendar.000webhostapp.com/listEvents.php"
    val requestQueue = Volley.newRequestQueue(context)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("list")
            eventsData.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val dateCal = registro.getString("dateCal")
                val event = registro.getString("event")
                eventsData.add(
                    Event(
                        dateCal, event
                    )
                )
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}