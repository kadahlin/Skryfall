package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

val Year = NumericQueryBuilder("year")

object Date {
    fun equals(year: Int, month: Int, day: Int): CardQuery = RawQuery("date=${formatDate(year, month, day)}")
    fun doesNotEqual(year: Int, month: Int, day: Int): CardQuery = RawQuery("date!=${formatDate(year, month, day)}")
    fun lessThan(year: Int, month: Int, day: Int): CardQuery = RawQuery("date<${formatDate(year, month, day)}")
    fun lessThanOrEqualTo(year: Int, month: Int, day: Int): CardQuery =
        RawQuery("date<=${formatDate(year, month, day)}")

    fun greaterThan(year: Int, month: Int, day: Int): CardQuery = RawQuery("date>${formatDate(year, month, day)}")
    fun greaterThanOrEqualTo(year: Int, month: Int, day: Int): CardQuery =
        RawQuery("date>=${formatDate(year, month, day)}")

    fun duringSet(code: String): CardQuery = RawQuery("date=$code")
    fun notDuringSet(code: String): CardQuery = RawQuery("date!=$code")
    fun beforeSet(code: String): CardQuery = RawQuery("date<$code")
    fun beforeOrDuringSet(code: String): CardQuery = RawQuery("date<=$code")
    fun afterSet(code: String): CardQuery = RawQuery("date>$code")
    fun afterOrDuringSet(code: String): CardQuery =
        RawQuery("date>=$code")

    private fun formatDate(year: Int, month: Int, day: Int): String {
        return "$year-$month-$day"
    }
}