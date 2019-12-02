package com.marklynch.flightsearch.parsing

import com.marklynch.flightsearch.data.Airport
import com.marklynch.flightsearch.data.City
import com.marklynch.flightsearch.data.Country

fun parseAirports(
    searchTerm: String,
    airports: List<Airport>,
    cities: List<City>,
    country: List<Country>
): List<Airport> {
    val airportLevelMatches = mutableListOf<AirportMatch>()
    for (airport in airports) {
        val airportMatch = matchAirportToSearchTerm(airport, searchTerm)
        if(airportMatch.matchLevel != AirportMatchLevel.NONE)
            airportLevelMatches.add(airportMatch)
    }

    val matchesAfterAirportLevel = airportLevelMatches.distinct()
    if(matchesAfterAirportLevel.size == 2)
        return matchesAfterAirportLevel.map { it.airports[0] }

    return emptyList()
}


enum class AirportMatchLevel { AIRPORT, CITY, COUNTRY, NONE }
class AirportMatch(
    val searchTerm: String,
    val airports: List<Airport>,
    val matchLevel: AirportMatchLevel
)

fun matchAirportToSearchTerm(
    airport: Airport,
    searchTerm: String
): AirportMatch {
    for (name in airport.names)
        if (searchTerm.contains(name, true))
            return AirportMatch(searchTerm, listOf(airport), AirportMatchLevel.AIRPORT)
    return AirportMatch(searchTerm, listOf(), AirportMatchLevel.NONE)
}

fun matchCityToSearchTerm(
    city: City,
    searchTerm: String
): AirportMatch {
    for (name in city.names)
        if (searchTerm.contains(name, true))
            return AirportMatch(searchTerm, city.airports, AirportMatchLevel.CITY)
    return AirportMatch(searchTerm, emptyList(), AirportMatchLevel.NONE)
}

fun matchCountryToSearchTerm(
    country: Country,
    searchTerm: String
): AirportMatch {
    for (name in country.names)
        if (searchTerm.contains(name, true))
            return AirportMatch(searchTerm, country.airports, AirportMatchLevel.COUNTRY)
    return AirportMatch(searchTerm, emptyList(), AirportMatchLevel.NONE)
}