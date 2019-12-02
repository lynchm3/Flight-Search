package com.marklynch.flightsearch.parsing

import com.joestelmach.natty.DateGroup
import com.joestelmach.natty.Parser
import java.util.*


fun parseDatesAndSort(searchTerm: String): List<Date> {
    val currentDate = Date()
    val dates: MutableList<Date> = mutableListOf()
    val dateGroups: List<DateGroup> = Parser().parse(searchTerm)

    for (dateGroup in dateGroups) {
        for (date in dateGroup.dates) {
            if (date.before(currentDate)) {
                val calendar: Calendar = Calendar.getInstance()
                calendar.time = date
                while (calendar.time < currentDate)
                    calendar.add(Calendar.YEAR, 1)
                dates.add(calendar.time)
            } else {
                dates.add(date)
            }
        }
    }

    return dates.sorted()
}
