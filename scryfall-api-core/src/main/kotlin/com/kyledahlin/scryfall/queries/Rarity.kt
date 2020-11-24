package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery

object Rarity {

    fun equals(rarity: CardRarity): CardQuery = RawQuery("r:${rarity.lc()}")
    fun doesNotEqual(rarity: CardRarity): CardQuery = RawQuery("-r:${rarity.lc()}")
    fun lessThan(rarity: CardRarity): CardQuery = RawQuery("r<:${rarity.lc()}")
    fun lessThanOrEqual(rarity: CardRarity): CardQuery = RawQuery("r<=:${rarity.lc()}")
    fun greaterThan(rarity: CardRarity): CardQuery = RawQuery("r>${rarity.lc()}")
    fun greaterThanOrEqual(rarity: CardRarity): CardQuery = RawQuery("r>=${rarity.lc()}")
    fun wereEverIn(rarity: CardRarity): CardQuery = RawQuery("in:${rarity.lc()}")
}

enum class CardRarity {
    COMMON, UNCOMMON, RARE, MYTHIC;

    internal fun lc() = name.toLowerCase()
}
