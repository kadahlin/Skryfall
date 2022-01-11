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

import com.kyledahlin.skryfall.objects.Card
import com.kyledahlin.skryfall.objects.CardSymbol
import com.kyledahlin.skryfall.objects.ManaCost
import com.kyledahlin.skryfall.objects.Ruling
import com.kyledahlin.skryfall.objects.Set
import com.kyledahlin.skryfall.queries.UniqueMode
import io.ktor.client.statement.*

interface SkryfallClient {

    companion object {
        fun createClient(logCalls: Boolean = false): SkryfallClient {
            return SkryfallCoroutineClientImpl(CallService(logCalls))
        }
    }

    suspend fun getSetWithCode(code: String): ClientResponse<Set>
    suspend fun getSetWithId(id: String): ClientResponse<Set>
    suspend fun getSetWithTcgPlayerId(id: String): ClientResponse<Set>
    suspend fun getSets(): ClientResponse<Collection<Set>>
    suspend fun getRulingsForMtgoId(id: String): ClientResponse<Collection<Ruling>>
    suspend fun getRulingsForArenaId(id: String): ClientResponse<Collection<Ruling>>
    suspend fun getRulingsForMultiverseId(
        id: String
    ): ClientResponse<Collection<Ruling>>

    suspend fun getRulingsForCardId(id: String): ClientResponse<Collection<Ruling>>
    suspend fun getRulingsForCodeAndNumber(
        code: String,
        number: String
    ): ClientResponse<Collection<Ruling>>

    suspend fun getAllSymbols(): ClientResponse<Collection<CardSymbol>>
    suspend fun getManaCostForSymbols(symbols: List<CardSymbol>): ClientResponse<ManaCost>

    suspend fun searchCards(
        query: CardQuery,
        uniqueMode: UniqueMode = UniqueMode.CARDS
    ): ClientResponse<Collection<Card>>

    suspend fun searchCards(nextPage: String): ClientResponse<Collection<Card>>
    suspend fun getCardByScryfallId(id: String): ClientResponse<Card>
    suspend fun getCardByCodeAndNumber(code: String, number: Int, language: String = ""): ClientResponse<Card>
}

internal class SkryfallCoroutineClientImpl(
    private val service: CallService
) : SkryfallClient {

    override suspend fun getSetWithCode(code: String): ClientResponse<Set> =
        service
            .getSetWithCode(code)
            .parseToResponse(CallService.Keys.SET)

    override suspend fun getSetWithId(id: String): ClientResponse<Set> =
        service
            .getSetWithId(id)
            .parseToResponse(CallService.Keys.SET)

    override suspend fun getSetWithTcgPlayerId(id: String): ClientResponse<Set> =
        service
            .getSetWithTcgPlayerId(id)
            .parseToResponse(CallService.Keys.SET)

    override suspend fun getSets(): ClientResponse<Collection<Set>> =
        service
            .getSets()
            .parseToListResponse()

    override suspend fun getRulingsForMtgoId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForMtgoId(id)
            .parseToListResponse()

    override suspend fun getRulingsForArenaId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForArenaId(id)
            .parseToListResponse()

    override suspend fun getRulingsForMultiverseId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForMultiverseId(id)
            .parseToListResponse()

    override suspend fun getRulingsForCardId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForCardId(id)
            .parseToListResponse()

    override suspend fun getRulingsForCodeAndNumber(code: String, number: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForCodeAndNumber(code, number)
            .parseToListResponse()

    override suspend fun getAllSymbols(): ClientResponse<Collection<CardSymbol>> =
        service
            .getAllSymbols()
            .parseToListResponse()

    override suspend fun getManaCostForSymbols(symbols: List<CardSymbol>): ClientResponse<ManaCost> =
        service
            .getManaCostForSymbols(symbols.joinToString("") { it.symbol })
            .parseToResponse(CallService.Keys.MANA_COST)

    override suspend fun searchCards(query: CardQuery, uniqueMode: UniqueMode): ClientResponse<Collection<Card>> =
        service
            .queryCards(query.toQueryString(), unique = uniqueMode.name.lowercase())
            .parseToListResponse()

    override suspend fun searchCards(nextPage: String): ClientResponse<Collection<Card>> =
        service
            .getCards(nextPage)
            .parseToListResponse()

    override suspend fun getCardByScryfallId(id: String): ClientResponse<Card> =
        service
            .getCardWithScryfallId(id)
            .parseToResponse(CallService.Keys.CARD)

    override suspend fun getCardByCodeAndNumber(code: String, number: Int, language: String): ClientResponse<Card> =
        service
            .getCardWithCodeAndNumber(code.lowercase(), number, language)
            .parseToResponse(CallService.Keys.CARD)

    private suspend inline fun <reified T> HttpResponse.parseToResponse(
        expectedObject: String,
    ): ClientResponse<T> = parseSingleResponse(expectedObject)

    private suspend inline fun <reified T> HttpResponse.parseToListResponse(): ClientResponse<Collection<T>> =
        parseList()
}

sealed class ClientResponse<T> {
    companion object {
        fun <T> success(t: T) = Success(t)
        fun <T> failure(e: ClientException) = Failure<T>(e)
    }
}

data class Success<T>(val data: T) : ClientResponse<T>()
data class Failure<T>(val exception: ClientException) : ClientResponse<T>()