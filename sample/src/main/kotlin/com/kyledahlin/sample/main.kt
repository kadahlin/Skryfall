package com.kyledahlin.sample

import com.kyledahlin.scryfall.coroutines.Success
import com.kyledahlin.scryfall.createClient
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main(args: Array<String>) = runBlocking {
    val coroutineClient = com.kyledahlin.scryfall.coroutines.createClient()
    println(coroutineClient.getRulingsForMtgoId("57934"))

    val symbols = coroutineClient.getAllSymbols()
    println(symbols)
    if(symbols is Success){
        val symbol = symbols.data.toList()[Random.nextInt(symbols.data.size)]
        val cost = coroutineClient.getManaCostForSymbols(listOf(symbol))
        println(cost)
    }

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