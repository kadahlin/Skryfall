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
package com.kyledahlin.skryfall

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

internal const val ENDPOINT = "https://api.scryfall.com"

/**
 * Client for interacting with the scryfall api and fetching data about magic cards
 */
internal class CallService(logCalls: Boolean) {

    private val _client = HttpClient {
        install(JsonFeature) {
            this.serializer = KotlinxSerializer(json = clientJson)
        }

        if (logCalls) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.INFO
            }
        }
    }

    private suspend fun get(suffix: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse =
        _client.get("$ENDPOINT$suffix", block)

    // ---------------------------------
    // Sets
    // ---------------------------------
    suspend fun getSetWithCode(code: String) = get("/sets/$code")

    // set with this exact id
    suspend fun getSetWithId(id: String) = get("/sets/$id")

    // set with this exact tcg player id
    suspend fun getSetWithTcgPlayerId(id: String) = get("/sets/tcgplayer/$id")

    // all sets
    suspend fun getSets() = get("/sets")

    // ---------------------------------
    // Rulings
    // ---------------------------------
    suspend fun getRulingsForMultiverseId(id: String) = get("/cards/multiverse/$id/rulings")

    suspend fun getRulingsForMtgoId(id: String) = get("/cards/mtgo/$id/rulings")

    suspend fun getRulingsForArenaId(id: String) = get("/cards/arena/$id/rulings")

    suspend fun getRulingsForCodeAndNumber(code: String, number: String) =
        get("/cards/multiverse/$code/$number/rulings")

    suspend fun getRulingsForCardId(id: String) = get("/cards/$id/rulings")

    // ---------------------------------
    // Symbols
    // ---------------------------------
    suspend fun getAllSymbols() = get("/symbology")

    suspend fun getManaCostForSymbols(cost: String) = get("/symbology/parse-mana") {
        parameter("cost", cost)
    }

    // ---------------------------------
    // Cards
    // ---------------------------------
    suspend fun queryCards(
        name: String,
        unique: String? = null,
        order: String? = null,
        dir: String? = null,
        includeExtras: Boolean? = null,
        includeMultilingual: Boolean? = null,
        includeVariations: Boolean? = null,
        page: Int = 1,
    ) = get("/cards/search") {
        listOf(
            "q" to name,
            "unique" to unique,
            "order" to order,
            "dir" to dir,
            "include_extras" to includeExtras,
            "include_multilingual" to includeMultilingual,
            "include_variations" to includeVariations,
            "page" to page
        ).forEach { parameter(it.first, it.second) }
    }

    suspend fun getCards(url: String) = get(url)

    suspend fun getCardWithScryfallId(
        id: String,
        format: String? = null,
        face: String? = null,
        version: String? = null,
    ) = get("/cards/$id") {
        listOf(
            "format" to format,
            "face" to face,
            "version" to version
        ).forEach { parameter(it.first, it.second) }
    }

    suspend fun getCardWithCodeAndNumber(
        code: String,
        number: Int,
        language: String = "",
    ) = get("/cards/$code/$number/$language")

    object Keys {
        const val SET = "set"
        const val DATA = "data"
        const val CARD = "card"
        const val OBJECT = "object"
        const val LIST = "list"
        const val NEXT_PAGE = "next_page"
        const val HAS_MORE = "has_more"
        const val MANA_COST = "mana_cost"
    }

    companion object {

        // Scryfall needs "+" to not be encoded to %2B
//        private val plusInterceptor = object : Interceptor {
//
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val request = chain.request()
//                val path = request.url.toString()
//
//                val string = path.replace("%2B", "+") // replace
//
//                val newRequest = request.newBuilder()
//                    .url(string)
//                    .build()
//
//                return chain.proceed(newRequest)
//            }
//        }
    }
}
