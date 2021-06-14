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
package com.kyledahlin.skryfall.callback

import com.kyledahlin.skryfall.*
import com.kyledahlin.skryfall.objects.Card
import com.kyledahlin.skryfall.objects.CardSymbol
import com.kyledahlin.skryfall.objects.ManaCost
import com.kyledahlin.skryfall.objects.Ruling
import com.kyledahlin.skryfall.queries.UniqueMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import com.kyledahlin.skryfall.objects.Set as ScryfallSet

interface SkryfallCallbackClient {
    fun getSetWithCode(code: String, onSuccess: (ScryfallSet) -> Unit, onFailure: (ClientException) -> Unit)
    fun getSetWithId(id: String, onSuccess: (ScryfallSet) -> Unit, onFailure: (ClientException) -> Unit)
    fun getSetWithTcgPlayerId(id: String, onSuccess: (ScryfallSet) -> Unit, onFailure: (ClientException) -> Unit)
    fun getSets(onSuccess: (Collection<ScryfallSet>) -> Unit, onFailure: (ClientException) -> Unit)
    fun getRulingsForMtgoId(id: String, onSuccess: (Collection<Ruling>) -> Unit, onFailure: (ClientException) -> Unit)
    fun getRulingsForArenaId(id: String, onSuccess: (Collection<Ruling>) -> Unit, onFailure: (ClientException) -> Unit)
    fun getRulingsForMultiverseId(
        id: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    )

    fun getRulingsForCardId(
        id: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    )

    fun getRulingsForCodeAndNumber(
        code: String,
        number: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    )

    fun getAllSymbols(onSuccess: (Collection<CardSymbol>) -> Unit, onFailure: (ClientException) -> Unit)
    fun getManaCostForSymbols(
        symbols: List<CardSymbol>,
        onSuccess: (ManaCost) -> Unit,
        onFailure: (ClientException) -> Unit
    )

    fun searchCard(
        query: CardQuery,
        unique: UniqueMode = UniqueMode.CARDS,
        onSuccess: (Collection<Card>) -> Unit,
        onFailure: (ClientException) -> Unit
    )

    fun getCardByScryfallId(id: String, onSuccess: (Card) -> Unit, onFailure: (ClientException) -> Unit)

    companion object {
        fun createClient(): SkryfallCallbackClient {
            return SkryfallCallbackClientImpl(CallService.create())
        }
    }
}

class SkryfallCallbackClientImpl(
    private val service: CallService
) : SkryfallCallbackClient {

    private val json = Json { ignoreUnknownKeys = true }

    override fun getSetWithCode(code: String, onSuccess: (ScryfallSet) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getSetWithCode(code)
            .executeAndFold(
                expectedObject = CallService.Keys.SET,
                {
                    onSuccess(
                        json.decodeFromJsonElement(ScryfallSet.serializer(), it)
                    )
                },
                onFailure = onFailure
            )
    }

    override fun getSetWithId(id: String, onSuccess: (ScryfallSet) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getSetWithId(id)
            .executeAndFold(
                expectedObject = CallService.Keys.SET,
                {
                    onSuccess(
                        json.decodeFromJsonElement(ScryfallSet.serializer(), it)
                    )
                },
                onFailure = onFailure
            )
    }

    override fun getSetWithTcgPlayerId(
        id: String,
        onSuccess: (ScryfallSet) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .getSetWithTcgPlayerId(id)
            .executeAndFold(
                expectedObject = CallService.Keys.SET,
                {
                    onSuccess(
                        json.decodeFromJsonElement(ScryfallSet.serializer(), it)
                    )
                },
                onFailure = onFailure
            )
    }

    override fun getSets(onSuccess: (Collection<ScryfallSet>) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getSets()
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getRulingsForMtgoId(
        id: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .getRulingsForMtgoId(id)
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getRulingsForArenaId(
        id: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .getRulingsForArenaId(id)
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getRulingsForMultiverseId(
        id: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .getRulingsForMultiverseId(id)
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getRulingsForCardId(
        id: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .getRulingsForCardId(id)
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getRulingsForCodeAndNumber(
        code: String,
        number: String,
        onSuccess: (Collection<Ruling>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .getRulingsForCodeAndNumber(code, number)
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getAllSymbols(onSuccess: (Collection<CardSymbol>) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getAllSymbols()
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getManaCostForSymbols(
        symbols: List<CardSymbol>,
        onSuccess: (ManaCost) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        val symbolsString = symbols.joinToString("") { it.symbol }
        service
            .getManaCostForSymbols(symbolsString)
            .executeAndFold(
                expectedObject = CallService.Keys.MANA_COST,
                {
                    onSuccess(json.decodeFromJsonElement(it))
                },
                onFailure = onFailure
            )
    }

    override fun searchCard(
        query: CardQuery,
        unique: UniqueMode,
        onSuccess: (Collection<Card>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .queryCards(query.toString(), unique.name.toLowerCase())
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }

    override fun getCardByScryfallId(id: String, onSuccess: (Card) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getCardWithScryfallId(id)
            .executeAndFold(
                expectedObject = CallService.Keys.CARD,
                {
                    onSuccess(json.decodeFromJsonElement(it))
                },
                onFailure = onFailure
            )
    }
}
