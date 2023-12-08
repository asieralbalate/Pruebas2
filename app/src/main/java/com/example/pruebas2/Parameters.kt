package com.example.pruebas2

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

//---------calendar-------------//
val eventsData = mutableStateListOf<Event>()
data class Event(val dateCal: String, val event: String)
var showDialogEvents = mutableStateOf(false)
var showDialogDiary = mutableStateOf(false)
var showDropDownMenu = mutableStateOf(false)