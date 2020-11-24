package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

val FlavorText
    get() = StringQuery("ft")

val Artist
    get() = StringQuery("a")

val Artists
    get() = NumericQueryBuilder("artists")

val Illustrations
    get() = NumericQueryBuilder("illustrations")

object Watermark {
    val text
        get() = StringQuery("wm")

    val hasAny: CardQuery get() = RawQuery("has:watermark")
}
