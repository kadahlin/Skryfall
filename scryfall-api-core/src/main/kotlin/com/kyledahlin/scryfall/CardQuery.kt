package com.kyledahlin.scryfall

@DslMarker
annotation class CardQueryMarker

@CardQueryMarker
interface CardQuery {
    fun toQueryString(): String
}

infix fun CardQuery.and(two: CardQuery): AndQuery {
    return AndQuery(this, two)
}

infix fun CardQuery.or(two: CardQuery): OrQuery {
    return OrQuery(this, two)
}

internal class RawQuery(private val _query: String) : CardQuery {
    override fun toQueryString() = _query
}

class NegatableQuery(private val _query: String) : CardQuery {
    override fun toQueryString() = _query

    fun negate(): NegatableQuery = if (_query.startsWith("-")) {
        NegatableQuery(_query.substring(1))
    } else {
        NegatableQuery(_query = "-$_query")
    }
}

internal fun negatableIsQuery(name: String): NegatableQuery = NegatableQuery("is:$name")

class AndQuery(val one: CardQuery, val two: CardQuery) : CompositeQuery() {

    override fun toQueryString(): String {
        if (one is EmptyQuery || two is EmptyQuery) {
            return if (one is EmptyQuery) two.toQueryString() else one.toQueryString()
        }
        return listOf(one, two).joinToString(" ") { it.toQueryString() }
    }
}

class OrQuery(val one: CardQuery, val two: CardQuery) : CompositeQuery() {

    override fun toQueryString(): String {
        if (one is EmptyQuery || two is EmptyQuery) {
            return if (one is EmptyQuery) two.toQueryString() else one.toQueryString()
        }
        val firstQuery = one.toQueryString()
        val secondQuery = two.toQueryString()
        return "(($firstQuery) or ($secondQuery))"
    }
}

abstract class CompositeQuery : CardQuery {
    protected var queries: MutableList<CardQuery> = mutableListOf()

//    private fun <T : CardQuery> initQuery(query: T, init: T.() -> Unit) {
//        query.init()
//        queries.add(query)
//    }
//
//    fun or(init: CompositeQuery.() -> Unit) {
//        val or = OrQuery().apply(init)
//        queries.add(or)
//    }
}

class EmptyQuery : CardQuery {
    override fun toQueryString() = ""
}
