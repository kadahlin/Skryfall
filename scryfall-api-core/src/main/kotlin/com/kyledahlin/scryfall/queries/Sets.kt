package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

val Sets
    get() = NumericQueryBuilder("sets")

object Set {
    fun withCode(code: String): CardQuery = RawQuery("e:$code")
    fun notInSet(code: String): CardQuery = RawQuery("-e:$code")
}

object Block {
    fun withCode(code: String): CardQuery = RawQuery("b:$code")
    fun notInSet(code: String): CardQuery = RawQuery("-b:$code")
}
