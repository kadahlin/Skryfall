package com.kyledahlin.scryfall.queries

object CardText {
    val oracle get() = StringQuery("oracle")
    val fullOracle get() = StringQuery("fullOracle")
    val keyword get() = StringQuery("keyword")
}
