package com.kyledahlin.scryfall.queries

object Prices {
    val usd
        get() = NumericQueryBuilder("usd")

    val eur
        get() = NumericQueryBuilder("eur")

    val tix
        get() = NumericQueryBuilder("tix")
}
