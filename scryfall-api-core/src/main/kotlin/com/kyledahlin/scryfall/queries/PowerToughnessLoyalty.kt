package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

val Loyalty
    get() = NumericQueryBuilder("loy")

object Power : NumericQueryBuilder("pow") {
    val isEqualToughness: CardQuery = RawQuery("pow=tou")
    val isNotEqualToughness: CardQuery = RawQuery("pow!=tou")
    val isLessThanToughness: CardQuery = RawQuery("pow<tou")
    val isLessThanOrEqualToughness: CardQuery = RawQuery("pow<=tou")
    val isGreaterThanToughness: CardQuery = RawQuery("pow>tou")
    val isGreaterThanOrEqualToToughness: CardQuery = RawQuery("pow>=tou")
}

object Toughness : NumericQueryBuilder("tou") {
    val isEqualPower: CardQuery = RawQuery("tou=pow")
    val isNotEqualPower: CardQuery = RawQuery("tou!=pow")
    val isLessThanPower: CardQuery = RawQuery("tou<pow")
    val isLessThanOrEqualPower: CardQuery = RawQuery("tou<=pow")
    val isGreaterThanPower: CardQuery = RawQuery("tou>pow")
    val isGreaterThanOrEqualToPower: CardQuery = RawQuery("tou>=pow")
}
