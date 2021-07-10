/*
Copyright 2021 Kyle Dahlin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.kyledahlin.sample

import com.kyledahlin.skryfall.CardQuery
import com.kyledahlin.skryfall.and
import com.kyledahlin.skryfall.coroutines.SkryfallCoroutineClient
import com.kyledahlin.skryfall.coroutines.Success
import com.kyledahlin.skryfall.or
import com.kyledahlin.skryfall.queries.*
import com.kyledahlin.skryfall.queries.Set.withCode
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
    val coroutineClient = SkryfallCoroutineClient.createClient(logCalls = true)

    val isLegendary = Type.contains("legendary")
    val goblin = Type.contains("goblin")
    val elf = Type.contains("elf")
    val isGoblinOrElf = goblin or elf
    val isNotGoblin = Type.contains("goblin")
    val isNotRare = Rarity.greaterThan(CardRarity.COMMON)
    val aboveOneDollar = Prices.usd.greaterThan(1)
    val hasWatermark = Watermark.hasAny
    val topHeavy = Power.isGreaterThanToughness
    val query: CardQuery = Type.contains("land") and (Artist.contains("titus") or Artist.contains("avon"))

    val cards = coroutineClient.searchCards(withCode("thb") and Language.matches("korean"))
    println(cards)

    val obliterator = coroutineClient.getCardByCodeAndNumber("NPH", 68)
    println(obliterator)

    println((coroutineClient.searchCards(CardText.name("woe strider") and Language.matches("korean")) as Success).data.first().printedName)
    println(
        (coroutineClient.searchCards(
            CardText.name("woe strider") and Games.printsFromArena,
            uniqueMode = UniqueMode.PRINTS
        ) as Success).data.map { it.id })
}