package com.example.pruebas2

import android.content.Context
import android.util.Log
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

fun checkDatabase(context: Context, callback: (MutableMap<String, IntArray>) -> Unit) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/checkDatabase.php"

    val req = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val dataMap: MutableMap<String, IntArray> = mutableMapOf()
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
            callback(dataMap)
        },
        { error ->
            error.printStackTrace()
        }
    )
    requestQueue.add(req)
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

fun insertDatabaseFuncionaSeguro(dateCal: String, selectedDiaryTask: String, context: Context) {
    val url = "https://dailyasiercalendar.000webhostapp.com/insertTaskDatabase.php"
    val requestQueue = Volley.newRequestQueue(context)
    val parameters = JSONObject()
    parameters.put("dateCal", dateCal)
    parameters.put("event", selectedDiaryTask)

    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        parameters,
        { response ->
            // Impresión para depuración
            Log.d("InsertDatabase", "Response: $response")
        },
        { error ->

            Log.e("InsertDatabase", "Error: ${error.message}", error)
        }
    )
    requestQueue.add(req)
}
fun insertDatabase(dateCal: String, selectedDiaryTask: String, context: Context) {
    val existingEvent = eventsData.find { it.dateCal == dateCal }

    if (existingEvent != null) {
        // Si ya hay un evento para la fecha proporcionada, concatena los eventos
        val updatedEvent = Event(dateCal, "${existingEvent.event}&&$selectedDiaryTask")

        // Actualiza la lista de eventos
        eventsData.removeAll { it.dateCal == dateCal }
        eventsData.add(updatedEvent)

        // Actualiza la base de datos
        updateDatabase(dateCal, eventsData.toString(), context)
    } else {
        // Si no hay eventos para la fecha proporcionada, inserta un nuevo registro
        val url = "https://dailyasiercalendar.000webhostapp.com/insertTaskDatabase.php"
        val requestQueue = Volley.newRequestQueue(context)
        val parameters = JSONObject()
        parameters.put("dateCal", dateCal)
        parameters.put("event", selectedDiaryTask)

        val req = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            { response ->
                // Impresión para depuración
                Log.d("InsertDatabase", "Response: $response")
            },
            { error ->
                // Impresión para depuración
                Log.e("InsertDatabase", "Error: ${error.message}", error)
            }
        )
        requestQueue.add(req)

        // Agrega el nuevo evento a la lista local
        eventsData.add(Event(dateCal, selectedDiaryTask))
    }
}

fun updateDatabase(dateCal: String, updatedEvent: String, context: Context) {
    clearDatabase(dateCal, context)
    insertDatabase(dateCal, updatedEvent, context)
}

fun clearDatabase(dateCal: String, context: Context) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/clearDatabase.php"
    val parameters = JSONObject()
    parameters.put("dateCal", dateCal)

    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        parameters,
        { response ->
            // Impresión para depuración
            Log.d("ClearDatabase", "Response: $response")
        },
        { error ->
            // Impresión para depuración
            Log.e("ClearDatabase", "Error: ${error.message}", error)
        }
    )
    requestQueue.add(req)
}


fun deleteAllCalendarEvents(contexto: Context) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "https://dailyasiercalendar.000webhostapp.com/deleteAllCalendarEvents.php"
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        null,
        { response ->

        }
    ) { error ->  }
    requestQueue.add(requerimiento)
}

fun deleteAllDiaryRecord(contexto: Context) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "https://dailyasiercalendar.000webhostapp.com/deleteAllDiaryRecord.php"
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        null,
        { response ->

        }
    ) { error ->  }
    requestQueue.add(requerimiento)
}