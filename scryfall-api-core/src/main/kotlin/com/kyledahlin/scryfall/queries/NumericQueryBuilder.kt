package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery
import com.kyledahlin.scryfall.and

open class NumericQueryBuilder internal constructor(private val name: String) {
    fun equals(value: Number): CardQuery = RawQuery("$name=$value")
    fun doesNotEqual(value: Number): CardQuery = RawQuery("$name!=$value")

    fun lessThan(value: Number): CardQuery = RawQuery("$name<$value")
    fun lessthanOrEqualTo(value: Number): CardQuery = RawQuery("$name<=$value")
    fun greaterThan(value: Number): CardQuery = RawQuery("$name>$value")
    fun greaterThanOrEqualTo(value: Number): CardQuery = RawQuery("$name>=$value")

    fun isBetween(start: Number, end: Number): CardQuery = RawQuery("$name>=$start") and RawQuery("$name<=$end")
}
