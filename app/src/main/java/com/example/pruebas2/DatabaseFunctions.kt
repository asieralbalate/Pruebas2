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

// Function to upload the content to the database checking the info before doing it
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
    checkDate(
        dateCal,
        { exists ->
            if (exists) {
                updateData(
                    dateCal,
                    selectedDiaryAdjective,
                    selectedWeatherAdjective,
                    selectedStepsAdjective,
                    selectedSpendAdjective,
                    selectedWeightAdjective,
                    selectedFoodAdjective,
                    selectedSleepAdjective,
                    context
                )
            } else {
                insertData(
                    dateCal,
                    selectedDiaryAdjective,
                    selectedWeatherAdjective,
                    selectedStepsAdjective,
                    selectedSpendAdjective,
                    selectedWeightAdjective,
                    selectedFoodAdjective,
                    selectedSleepAdjective,
                    context
                )
            }
        },
        context
    )
}

// Function to check if the date exist returning boolean
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
        Log.e("checkDate", "Error: ${error.message}", error)
    }
    requestQueue.add(req)
}

// Function to insert the data from de diary to de database
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
            Log.d("insertData", "Response: $response")
        },
        { error ->
            Log.e("insertData", "Error: ${error.message}", error)
        }
    )
    requestQueue.add(req)
}

// Function to update the data from de diary to de database
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
            Log.d("updateData", "Response: $response")
        },
        { error ->
            Log.e("updateData", "Error: ${error.message}", error)
        }
    )
    requestQueue.add(req)
}

// Function used to list data from database and return as a mutable map
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

// Function that list events from eventsCalendar
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
                val reg = jsonArray.getJSONObject(i)
                val dateCal = reg.getString("dateCal")
                val event = reg.getString("event")
                eventsData.add(
                    Event(
                        dateCal, event
                    )
                )
            }
        },
        { error ->
            Log.e("listEvents", "Error: ${error.message}", error)
        }
    )
    requestQueue.add(jsonObjectRequest)
}

// This function choose between insert or update the content of eventsCalendar
fun insertOrAddDatabase(dateCal: String, selectedDiaryTask: String, context: Context) {
    val existingEvent = eventsData.find { it.dateCal == dateCal }
    if (existingEvent != null) {
        val newString = "${existingEvent.event}&&$selectedDiaryTask"
        updateDatabase(dateCal, newString, context)
        eventsData.removeAll { it.dateCal == dateCal }
        eventsData.add(Event(dateCal, newString))
    } else {
        insertDatabaseAux(dateCal, selectedDiaryTask, context)
        eventsData.add(Event(dateCal, selectedDiaryTask))
    }
}

// This function insert the events into de database
fun insertDatabaseAux(dateCal: String, selectedDiaryTask: String, context: Context) {
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
            try {
                if (response.getBoolean("success")) {
                    val rowsAffected = response.getInt("rows_affected")
                    Log.d("InsertDatabase", "affected: $rowsAffected")
                } else {
                    val errorMessage = response.getString("error")
                    Log.e("InsertDatabase", "Error: $errorMessage")
                }
            } catch (e: JSONException) {
                Log.e("InsertDatabase", "Error response JSON: ${e.message}", e)
            }
        },
        { error ->
            Log.e("InsertDatabase", "Error server: ${error.message}", error)
        }
    )

    requestQueue.add(req)
}

// This functions updates de database deleting it before insert again with the new content
fun updateDatabase(dateCal: String, updatedEvent: String, context: Context) {
    Log.d("LastUpdate", "Response: $dateCal + $updatedEvent")
    clearDatabase(dateCal, context)
    insertDatabaseAux(dateCal, updatedEvent, context)
}

// This function clears the row of a database based on a date as parameter
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
            Log.d("ClearDatabase", "Response:$response")
        },
        { error ->
            Log.e("ClearDatabase", "Error: ${error.message}", error)
            error.printStackTrace()
        }
    )
    requestQueue.add(req)
}

// This functions clear oll the events of a date from eventsCalendar
fun clearSingleDateDatabase(dateCal: String, context: Context) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/clearDatabase.php"
    val parameters = JSONObject()
    parameters.put("dateCal", dateCal)

    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        parameters,
        { response ->

            Log.d("ClearDatabase", "Response: $response")
        },
        { error ->

            Log.e("ClearDatabase", "Error: ${error.message}", error)
        }
    )
    requestQueue.add(req)
    eventsData.removeAll { it.dateCal == dateCal }
}


// This function deletes only one row of the events listed in each date
fun deleteOneRowDatabase(dateCal: String, rowToDelete: String, context: Context) {
    val eventsToDelete = eventsData.filter { it.dateCal == dateCal }
    for (eventToDelete in eventsToDelete) {
        val updated = eventToDelete.event.split("&&")
        val auxUpdated = updated.filter { it != rowToDelete }
        val updatedString = auxUpdated.joinToString("&&", prefix = "")
        Log.d("UPDATEString", "Response: $updatedString")
        updateDatabase(dateCal, updatedString, context)
        if (updatedString == ""){
            clearSingleDateDatabase(dateCal, context)
        } else {
            eventsData.replaceAll {
                if (it.dateCal == dateCal) {
                    it.copy(dateCal = dateCal, event = updatedString)
                } else {
                    it
                }
            }
        }
        Log.d("UPDATEString", "Response: ${eventsData.find { it.dateCal == dateCal }}")
    }
}

// This function deletes all data from eventsCalendar
fun deleteAllCalendarEvents(context: Context) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/deleteAllCalendarEvents.php"
    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        null,
        { response ->
            Log.d("deleteAllCalendarEvents", "Response: $response")
        }
    ) { error ->
        Log.e("deleteAllCalendarEvents", "Error: ${error.message}", error)
    }
    requestQueue.add(req)
    eventsData.clear()
}
// This function deletes all data from diaryCalendar
fun deleteAllDiaryRecord(context: Context) {
    val requestQueue = Volley.newRequestQueue(context)
    val url = "https://dailyasiercalendar.000webhostapp.com/deleteAllDiaryRecord.php"
    val req = JsonObjectRequest(
        Request.Method.POST,
        url,
        null,
        { response ->
            Log.d("deleteAllDiaryRecord", "Response: $response")
        }
    ) { error ->
        Log.e("deleteAllDiaryRecord", "Error: ${error.message}", error)
    }
    requestQueue.add(req)
}
