package com.marklynch.flightsearch.data

data class Airport(val names: Array<String>, val city: String, val country: String) {

    fun matchesSearchTerm(searchTerm: String): Boolean {
        for (name in names)
            if (name == searchTerm)
                return true
        if (city == searchTerm)
            return true
        if (country == searchTerm)
            return true
        return false
    }

}