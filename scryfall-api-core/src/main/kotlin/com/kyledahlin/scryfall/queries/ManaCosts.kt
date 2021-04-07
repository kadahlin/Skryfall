package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.CardQuery
import com.kyledahlin.scryfall.RawQuery
import com.kyledahlin.scryfall.objects.CardColor

object ManaCost {
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



