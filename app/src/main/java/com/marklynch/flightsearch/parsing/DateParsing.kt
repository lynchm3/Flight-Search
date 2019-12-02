package com.marklynch.flightsearch.parsing

import com.joestelmach.natty.DateGroup
import com.joestelmach.natty.Parser
import java.util.*


fun parseDatesCleanAndSort(searchTerm: String): List<Date> {
    var dates:List<Date> = parseDatesFromString(searchTerm)
    dates = dates.map{ pushDateForwardIfBeforeToday(it)}
    return dates.sorted()
}

private fun parseDatesFromString(searchTerm: String): List<Date> {
    val dates: MutableList<Date> = mutableListOf()
    val dateGroups: List<DateGroup> = Parser().parse(searchTerm)

    for (dateGroup in dateGroups) {
        for (date in dateGroup.dates) {
                dates.add(date)
        }
    }

    return dates

}

private fun pushDateForwardIfBeforeToday(dateToCheck: Date): Date {
    val currentDate = Date()
    val currentDateCalendar = Calendar.getInstance()

    val dateToCheckCalendar = Calendar.getInstance()
    dateToCheckCalendar.time = dateToCheck

    //If date is today it's fine
    if (dateToCheckCalendar sameDayAs currentDateCalendar)
        return dateToCheck

    //If date isnt in the future incrememnt the date until it is
    if (dateToCheck.before(currentDate)) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = dateToCheck
        while (calendar.time < currentDate)
            calendar.add(Calendar.YEAR, 1)
        return calendar.time
    }

    return dateToCheck

}

infix fun Calendar.sameDayAs(other: Calendar): Boolean {
    return this.get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)
                && this.get(Calendar.YEAR) == other.get(Calendar.YEAR)
}
