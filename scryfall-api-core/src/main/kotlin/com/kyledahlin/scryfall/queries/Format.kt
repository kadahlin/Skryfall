package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object Format {
    fun isLegalIn(format: Formats): CardQuery = RawQuery("f:${format.name}")
    fun isBannedIn(format: Formats): CardQuery = RawQuery("banned:${format.name}")
    fun isRestrictedIn(format: Formats): CardQuery = RawQuery("restricted:${format.name}")

    enum class Formats {
        standard,
        future,
        historic,
        pioneer,
        modern,
        legacy,
        pauper,
        vintage,
        penny,
        commander,
        brawl,
        duel,
        oldschool
    }
}
