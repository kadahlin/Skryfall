package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.NegatableQuery
import com.kyledahlin.scryfall.RawQuery

object Language {
    // TODO: can probably find some type safe values for this
    fun matches(value: String) = NegatableQuery("lang:$value")

    val firstPrinting: CardQuery get() = RawQuery("new:language")

    fun printedIn(value: String) = NegatableQuery("in:$value")
}
