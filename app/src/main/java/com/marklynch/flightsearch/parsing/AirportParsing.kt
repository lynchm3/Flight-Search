package com.marklynch.flightsearch.parsing

import com.marklynch.flightsearch.data.Airport


enum class AirportMatchLevel{AIRPORT, CITY, COUNTRY, NONE}
class AirportMatch(val searchTerm: String, val airport: Airport, val matchLevel:AirportMatchLevel)

fun matchAiportToSearchTerm(
    airport: Airport,
    searchTerm: String
): AirportMatch {
    for (name in airport.names)
        if (searchTerm.contains(name, true))
            return AirportMatch(searchTerm, airport, AirportMatchLevel.AIRPORT)
    if (searchTerm.contains(airport.city, true))
        return AirportMatch(searchTerm, airport, AirportMatchLevel.CITY)
    if (searchTerm.contains(airport.country, true))
        return AirportMatch(searchTerm, airport, AirportMatchLevel.COUNTRY)
    return AirportMatch(searchTerm, airport, AirportMatchLevel.NONE)
}