package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object Type {

    fun contains(type: String): CardQuery = RawQuery("t:$type")

    fun doesNotContain(type: String): CardQuery = RawQuery("-t:$type")
}
