package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object Name {
    fun isExactly(name: String): CardQuery = RawQuery("!\"$name\"")
}
