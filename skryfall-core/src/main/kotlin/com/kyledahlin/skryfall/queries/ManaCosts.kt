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
package com.kyledahlin.skryfall.queries

import com.kyledahlin.skryfall.CardQuery
import com.kyledahlin.skryfall.RawQuery

object ManaCostQuery {
    val isEven: CardQuery get() = RawQuery("cmc:even")
    val isOdd: CardQuery get() = RawQuery("cmc:odd")

    val cmc = NumericQueryBuilder("cmc")

    fun isEqual(vararg symbols: ManaSymbol) = buildSymbolQuery("m:", symbols)
    fun isLessThan(vararg symbols: ManaSymbol) = buildSymbolQuery("m<", symbols)
    fun isLessThanOrEqual(vararg symbols: ManaSymbol) = buildSymbolQuery("m<=", symbols)
    fun isMoreThan(vararg symbols: ManaSymbol) = buildSymbolQuery("m>", symbols)
    fun isMoreThanOrEqual(vararg symbols: ManaSymbol) = buildSymbolQuery("m>=", symbols)
}

class Devotion {
    fun isEqual(vararg symbols: ManaSymbol) = buildSymbolQuery("devotion:", symbols)
    fun isLessThan(vararg symbols: ManaSymbol) = buildSymbolQuery("devotion<", symbols)
    fun isLessThanOrEqual(vararg symbols: ManaSymbol) = buildSymbolQuery("devotion<=", symbols)
    fun isMoreThan(vararg symbols: ManaSymbol) = buildSymbolQuery("devotion>", symbols)
    fun isMoreThanOrEqual(vararg symbols: ManaSymbol) = buildSymbolQuery("devotion>=", symbols)
}

// TODO: error handling
private fun buildSymbolQuery(prefix: String, symbols: Array<out ManaSymbol>): CardQuery =
    RawQuery(prefix + symbols.joinToString(separator = "") { it.raw })

sealed class ManaSymbol(val raw: String) {
    object White : ManaSymbol("{W}")
    object Black : ManaSymbol("{B}")
    object Blue : ManaSymbol("{U}")
    object Green : ManaSymbol("{G}")
    object Red : ManaSymbol("{R}")
    object Snow : ManaSymbol("{S}")
    object Colorless : ManaSymbol("{C}")
    object TwoOrWhite : ManaSymbol("{2/W}")
    object TwoOrBlack : ManaSymbol("{2/B}")
    object TwoOrGreen : ManaSymbol("{2/G}")
    object TwoOrBlue : ManaSymbol("{2/U}")
    object TwoOrRed : ManaSymbol("{2/R}")
    object WhiteOrBlack : ManaSymbol("{W/B}")
    object WhiteOrBlue : ManaSymbol("{W/U}")
    object WhiteOrGreen : ManaSymbol("{W/G}")
    object WhiteOrRed : ManaSymbol("{W/R}")
    object GreenOrRed : ManaSymbol("{G/R}")
    object GreenOrBlack : ManaSymbol("{G/B}")
    object GreenOrBlue : ManaSymbol("{G/U}")
    object RedOrBlue : ManaSymbol("{R/U}")
    object RedOrBlack : ManaSymbol("{R/B}")
    object BlueOrBlack : ManaSymbol("{U/B}")
    object WhitePhyrexian : ManaSymbol("{W/P}")
    object BlackPhyrexian : ManaSymbol("{B/P}")
    object BluePhyrexian : ManaSymbol("{U/P}")
    object GreenPhyrexian : ManaSymbol("{G/P}")
    object RedPhyrexian : ManaSymbol("{R/P}")

    class Generic(cost: Int) : ManaSymbol(cost.toString())
}



