package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object CardText {
    val oracle get() = StringQuery("oracle")
    val fullOracle get() = StringQuery("fullOracle")
    val keyword get() = StringQuery("keyword")
    fun name(name: String): CardQuery = RawQuery("\"$name\"")
}
