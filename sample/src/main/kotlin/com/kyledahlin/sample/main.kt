package com.kyledahlin.sample

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.and
import com.kyledahlin.scryfall.coroutines.Success
import com.kyledahlin.scryfall.or
import com.kyledahlin.scryfall.queries.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val coroutineClient = com.kyledahlin.scryfall.coroutines.createClient()
//    println(coroutineClient.getRulingsForMtgoId("57934"))
//
//    val symbols = coroutineClient.getAllSymbols()
//    println(symbols)
//    if (symbols is Success) {
//        val symbol = symbols.data.toList()[Random.nextInt(symbols.data.size)]
//        val cost = coroutineClient.getManaCostForSymbols(listOf(symbol))
//        println(cost)
//    }

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
    println(query.toQueryString())
    println((coroutineClient.searchCards(query) as Success).data.size)
//    print(coroutineClient.searchCards(query))
//    println(createClient().getSetWithId("a4a0db50-8826-4e73-833c-3fd934375f96", {
//        println("client got set ${it.name}")
//    }, {
//        println("client got failure $it")
//    }))
//
//    println(createClient().getSetWithTcgPlayerId("1857", {
//        println("client got set ${it.name}")
//    }, {
//        println("client got failure $it")
//    }))
////
//    println(createClient().getSets({
//        println("client got ${it.size} items")
//    }, {
//        println("client got failure $it")
//    }))

//    println(createClient().getRulingsForMtgoId("57934", {
//        println("client got set ${it.size} rulings")
//    }, {
//        println("client got failure for mtgo rulings $it")
//    }))


}