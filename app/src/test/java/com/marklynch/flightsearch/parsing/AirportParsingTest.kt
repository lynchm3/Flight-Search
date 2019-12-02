package com.marklynch.flightsearch.parsing

import com.marklynch.flightsearch.data.Airport
import junit.framework.Assert.assertEquals
import org.junit.Test

class AirportParsingTest {

    @Test
    fun `Test Airport Level Match`()
    {
        val searchTerm = """Manchester to DubliN AirPort
         for one adult
         leaving today"""

        val airport =
            Airport(listOf("Dublin Airport"), "Dublin", "Ireland")

        val airportMatch= matchAiportToSearchTerm(airport, searchTerm)

        assertEquals("Did not match at the airport level", AirportMatchLevel.AIRPORT, airportMatch.matchLevel)
        assertEquals("Airport in match object incorrect", airport, airportMatch.airport)
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }

    @Test
    fun `Test City Level Match`()
    {
        val searchTerm = """Manchester to dUbLin
         for one adult
         leaving today"""

        val airport =
            Airport(listOf("Dublin Airport"), "Dublin", "Ireland")

        val airportMatch= matchAiportToSearchTerm(airport, searchTerm)

        assertEquals("Did not match at the airport level", AirportMatchLevel.CITY, airportMatch.matchLevel)
        assertEquals("Airport in match object incorrect", airport, airportMatch.airport)
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }

    @Test
    fun `Test Country Level Match`()
    {
        val searchTerm = """Manchester to irelAnd
         for one adult
         leaving today"""

        val airport =
            Airport(listOf("Dublin Airport"), "Dublin", "Ireland")

        val airportMatch= matchAiportToSearchTerm(airport, searchTerm)

        assertEquals("Did not match at the airport level", AirportMatchLevel.COUNTRY, airportMatch.matchLevel)
        assertEquals("Airport in match object incorrect", airport, airportMatch.airport)
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }

    @Test
    fun `Test no Match`()
    {
        val searchTerm = """Manchester to Barcelona
         for one adult
         leaving today"""

        val airport =
            Airport(listOf("Dublin Airport"), "Dublin", "Ireland")

        val airportMatch= matchAiportToSearchTerm(airport, searchTerm)

        assertEquals("Did not match at the airport level", AirportMatchLevel.NONE, airportMatch.matchLevel)
        assertEquals("Airport in match object incorrect", airport, airportMatch.airport)
        assertEquals("Search tem in match object incorrect", searchTerm, airportMatch.searchTerm)
    }
}