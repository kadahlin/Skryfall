package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object Cube {
    fun matches(name: String): CardQuery = RawQuery("cube:$name")
    fun doesNotMatch(name: String): CardQuery = RawQuery("-cube:$name")
}
