package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

class StringQuery(private val name: String) {
    fun contains(value: String): CardQuery = RawQuery("$name:\"$value\"")

    fun doesNotContain(value: String): CardQuery = RawQuery("-$name:\"$value\"")
}
