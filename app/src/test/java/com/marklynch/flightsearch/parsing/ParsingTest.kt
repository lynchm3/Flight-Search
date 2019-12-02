package com.marklynch.flightsearch.parsing

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class ParsingTest {
    @Test
    fun `Test date parsing`() {

//        val currentDate:Date = Date()
        val currentDateCalendar:Calendar = Calendar.getInstance()

        val searchTerm = """Manchester to Barcelona
         for one adult
         returning on the 30th of December
         going out on the 31st of December"""

        val parsedDates = parseDatesAndSort(searchTerm)
        val calendar = Calendar.getInstance()

        assertEquals("Incorrect amount of dates parsed", 2, parsedDates.size)

        calendar.time = parsedDates[0]
        assertEquals("Day of month for first date incorrect", 30, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals("Month for first date incorrect", Calendar.DECEMBER, calendar.get(Calendar.MONTH))
        assertEquals("Year for first date incorrect", currentDateCalendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR))

        calendar.time = parsedDates[1]
        assertEquals("Day of month for second date incorrect", 31, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals("Month for second date incorrect", Calendar.DECEMBER, calendar.get(Calendar.MONTH))
        assertEquals("Year for second date incorrect", currentDateCalendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR))
    }

//    @Test
//    fun `Test date parsing return next year`() {
//        val searchTerm = """Manchester to Barcelona
//         for one adult
//         returning on the 19th of October
//         going out on the 1st of January"""
//
//        val parsedDates = parseDatesAndSort(searchTerm)
//        val calendar = Calendar.getInstance()
//
//        assertEquals("Incorrect amount of dates parsed", 2, parsedDates.size)
//
//        calendar.time = parsedDates[0]
//        assertEquals("Day of month for first date incorrect", 14, calendar.get(Calendar.DAY_OF_MONTH))
//        assertEquals("Month for first date incorrect", Calendar.OCTOBER, calendar.get(Calendar.MONTH))
//
//        calendar.time = parsedDates[1]
//        assertEquals("Day of month for second date incorrect", 19, calendar.get(Calendar.DAY_OF_MONTH))
//        assertEquals("Month for second date incorrect", Calendar.OCTOBER, calendar.get(Calendar.MONTH))
//    }
}
