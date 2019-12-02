package com.marklynch.flightsearch.parsing

import com.marklynch.flightsearch.data.Airport
import com.marklynch.flightsearch.data.City
import com.marklynch.flightsearch.data.Country
import org.junit.Assert.assertEquals
import org.junit.Test

val DUBLIN_AIRPORT = Airport(listOf("Dublin Airport"))
val DUBLIN = City(listOf("Dublin"), listOf(DUBLIN_AIRPORT))
val IRELAND = Country(listOf("Ireland"), listOf(DUBLIN), listOf(DUBLIN_AIRPORT))

class AirportParsingTest {


    @Test
    fun `Test Airport Level Match`() {
        val searchTerm = """Manchester to DubliN AirPort
         for one adult
         leaving today"""

        val airport = DUBLIN_AIRPORT

        val airportMatch = matchAirportToSearchTerm(airport, searchTerm)

        assertEquals(
            "Did not match at the airports level",
            AirportMatchLevel.AIRPORT,
            airportMatch.matchLevel
        )
        assertEquals("Airport in match object incorrect", airport, airportMatch.airports[0])
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }

    @Test
    fun `Test City Level Match`() {
        val searchTerm = """Manchester to dUbLin
         for one adult
         leaving today"""

        val city = DUBLIN

        val airportMatch = matchCityToSearchTerm(city, searchTerm)

        assertEquals(
            "Did not match at the airports level",
            AirportMatchLevel.CITY,
            airportMatch.matchLevel
        )
        assertEquals("Airport in match object incorrect", city.airports, airportMatch.airports)
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }

    @Test
    fun `Test Country Level Match`() {
        val searchTerm = """Manchester to irelAnd
         for one adult
         leaving today"""

        val country = IRELAND

        val airportMatch = matchCountryToSearchTerm(country, searchTerm)

        assertEquals(
            "Did not match at the airports level",
            AirportMatchLevel.COUNTRY,
            airportMatch.matchLevel
        )
        assertEquals("Airport in match object incorrect", country.airports, airportMatch.airports)
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }

    @Test
    fun `Test no Match`() {
        val searchTerm = """Manchester to Barcelona
         for one adult
         leaving today"""

        val airport = DUBLIN_AIRPORT

        val airportMatch = matchAirportToSearchTerm(airport, searchTerm)

        assertEquals(
            "Did not match at the airports level",
            AirportMatchLevel.NONE,
            airportMatch.matchLevel
        )
        assertEquals(
            "Airport in match object incorrect",
            emptyList<Airport>(),
            airportMatch.airports
        )
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }
}