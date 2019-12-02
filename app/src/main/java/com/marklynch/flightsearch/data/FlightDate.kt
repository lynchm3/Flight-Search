package com.marklynch.flightsearch.data

import java.util.*

data class FlightDate(val date: Date)
{
    val patterns:Array<String>  = arrayOf("on x", "leaving x", "departing x", "returning x", "coming back x")
}