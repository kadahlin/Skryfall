package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.NegatableQuery

object Games {
    val availableInPaper
        get() = NegatableQuery("in:paper")

    val availableInArena
        get() = NegatableQuery("in:arena")

    val availableInMtgo
        get() = NegatableQuery("in:mtgo")

    val printsFromPaper
        get() = NegatableQuery("game:paper")

    val printsFromArena
        get() = NegatableQuery("game:arena")

    val printsFromMtgo
        get() = NegatableQuery("game:mtgo")
}
