package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object Border {
    val white
        get(): CardQuery = RawQuery("border:white")

    val black
        get(): CardQuery = RawQuery("border:black")

    val silver
        get(): CardQuery = RawQuery("border:silver")

    val borderless
        get(): CardQuery = RawQuery("border:borderless")
}

object Frame {
    val is1993
        get(): CardQuery = RawQuery("frame:1993")

    val is1997
        get(): CardQuery = RawQuery("frame:1997")

    val is2003
        get(): CardQuery = RawQuery("frame:2003")

    val is2015
        get(): CardQuery = RawQuery("frame:2015")

    val isFuture
        get(): CardQuery = RawQuery("frame:future")

    val isLegendary
        get(): CardQuery = RawQuery("frame:legendary")

    val isColorshifted
        get(): CardQuery = RawQuery("frame:colorshifted")

    val isTombstone
        get(): CardQuery = RawQuery("frame:tombstone")

    val isNyxtouched
        get(): CardQuery = RawQuery("frame:nyxtouched")

    val isNew
        get():CardQuery = RawQuery("new:frame")

    val isOld
        get(): CardQuery = RawQuery("is:old")


}