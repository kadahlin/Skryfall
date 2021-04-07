package com.kyledahlin.sample

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.MtgClient
import com.kyledahlin.scryfall.and
import com.kyledahlin.scryfall.coroutines.Success
import com.kyledahlin.scryfall.or
import com.kyledahlin.scryfall.queries.*
import com.kyledahlin.scryfall.queries.Set.withCode
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
    val coroutineClient = com.kyledahlin.scryfall.coroutines.createClient()

    val isLegendary = Type.contains("legendary")
    val goblin = Type.contains("goblin")
    val elf = Type.contains("elf")
    val isGolbinOrElf = goblin or elf
    val isNotGoblin = Type.contains("goblin")
    val isNotRare = Rarity.greaterThan(CardRarity.COMMON)
    val aboveOneDollar = Prices.usd.greaterThan(1)
    val hasWatermark = Watermark.hasAny
    val topHeavy = Power.isGreaterThanToughness
    val query: CardQuery = Type.contains("land") and (Artist.contains("titus") or Artist.contains("avon"))

    val cards = (coroutineClient.searchCards(withCode("jmp")) as Success).data.filter { it.name.contains("strider") }
    println(cards.map { it.name })

    println((coroutineClient.searchCards(CardText.name("woe strider")) as Success).data.size)
    println((coroutineClient.searchCards(CardText.name("woe strider") and Games.printsFromArena, uniqueMode = MtgClient.UniqueMode.PRINTS) as Success).data.map { it.id })
}