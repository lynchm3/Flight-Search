package com.marklynch.flightsearch.parsing

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ParsingTest {
    @Test
    fun `Test date parsing`() {

        val currentDateCalendar:Calendar = Calendar.getInstance()

        val searchTerm = """Manchester to Barcelona
         for one adult
         returning on the 30th of December
         going out on the 31st of December"""

        val parsedDates = parseDatesCleanAndSort(searchTerm)
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

    @Test
    fun `Test date parsing dates implied next year`() {

        val currentDateCalendar:Calendar = Calendar.getInstance()

        val searchTerm = """Manchester to Barcelona
         for one adult
         leaving January 1st
         coming back January 2nd"""

        val parsedDates = parseDatesCleanAndSort(searchTerm)
        val calendar = Calendar.getInstance()

        assertEquals("Incorrect amount of dates parsed", 2, parsedDates.size)

        calendar.time = parsedDates[0]
        assertEquals("Day of month for first date incorrect", 1, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals("Month for first date incorrect", Calendar.JANUARY, calendar.get(Calendar.MONTH))
        assertEquals("Year for first date incorrect", currentDateCalendar.get(Calendar.YEAR) +1, calendar.get(Calendar.YEAR))

        calendar.time = parsedDates[1]
        assertEquals("Day of month for second date incorrect", 2, calendar.get(Calendar.DAY_OF_MONTH))
        assertEquals("Month for second date incorrect", Calendar.JANUARY, calendar.get(Calendar.MONTH))
        assertEquals("Year for second date incorrect", currentDateCalendar.get(Calendar.YEAR) +1, calendar.get(Calendar.YEAR))
    }

    @Test
    fun `Test date parsing dates coming and going today`() {

        val currentDateCalendar:Calendar = Calendar.getInstance()

        val searchTerm = """Manchester to Barcelona
         for one adult
         leaving ${SimpleDateFormat("MMM dd").format(currentDateCalendar.time)}
         coming back ${SimpleDateFormat("MMM dd").format(currentDateCalendar.time)}"""

        val parsedDates = parseDatesCleanAndSort(searchTerm)
        val parsedDateCalendar = Calendar.getInstance()

        assertEquals("Incorrect amount of dates parsed", 2, parsedDates.size)

        parsedDateCalendar.time = parsedDates[0]
        assertEquals("Day of month for first date incorrect", currentDateCalendar.get(Calendar.DAY_OF_MONTH), parsedDateCalendar.get(Calendar.DAY_OF_MONTH))
        assertEquals("Month for first date incorrect", currentDateCalendar.get(Calendar.MONTH), parsedDateCalendar.get(Calendar.MONTH))
        assertEquals("Year for first date incorrect", currentDateCalendar.get(Calendar.YEAR), parsedDateCalendar.get(Calendar.YEAR))

        parsedDateCalendar.time = parsedDates[1]
        assertEquals("Day of month for second date incorrect", currentDateCalendar.get(Calendar.DAY_OF_MONTH), parsedDateCalendar.get(Calendar.DAY_OF_MONTH))
        assertEquals("Month for second date incorrect", currentDateCalendar.get(Calendar.MONTH), parsedDateCalendar.get(Calendar.MONTH))
        assertEquals("Year for second date incorrect", currentDateCalendar.get(Calendar.YEAR), parsedDateCalendar.get(Calendar.YEAR))
    }
}
