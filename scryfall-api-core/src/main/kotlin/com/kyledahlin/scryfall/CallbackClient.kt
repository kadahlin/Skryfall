package com.kyledahlin.scryfall

import com.kyledahlin.scryfall.objects.*
import com.kyledahlin.scryfall.objects.Set
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

interface MtgClient {
    fun getSetWithCode(code: String, onSuccess: (Set) -> Unit, onFailure: (ClientException) -> Unit)
    fun getSetWithId(id: String, onSuccess: (Set) -> Unit, onFailure: (ClientException) -> Unit)
    fun getSetWithTcgPlayerId(id: String, onSuccess: (Set) -> Unit, onFailure: (ClientException) -> Unit)
    fun getSets(onSuccess: (Collection<Set>) -> Unit, onFailure: (ClientException) -> Unit)
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

    fun searchCard(query: CardQuery, onSuccess: (Collection<Card>) -> Unit, onFailure: (ClientException) -> Unit)
}

class MtgClientImpl(
    private val service: CallService
) : MtgClient {

    private val json = Json { ignoreUnknownKeys = true }

    override fun getSetWithCode(code: String, onSuccess: (Set) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getSetWithCode(code)
            .executeAndFold(
                expectedObject = CallService.Keys.SET,
                {
                    onSuccess(
                        json.decodeFromJsonElement(Set.serializer(), it)
                    )
                },
                onFailure = onFailure
            )
    }

    override fun getSetWithId(id: String, onSuccess: (Set) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getSetWithId(id)
            .executeAndFold(
                expectedObject = CallService.Keys.SET,
                {
                    onSuccess(
                        json.decodeFromJsonElement(Set.serializer(), it)
                    )
                },
                onFailure = onFailure
            )
    }

    override fun getSetWithTcgPlayerId(id: String, onSuccess: (Set) -> Unit, onFailure: (ClientException) -> Unit) {
        service
            .getSetWithTcgPlayerId(id)
            .executeAndFold(
                expectedObject = CallService.Keys.SET,
                {
                    onSuccess(
                        json.decodeFromJsonElement(Set.serializer(), it)
                    )
                },
                onFailure = onFailure
            )
    }

    override fun getSets(onSuccess: (Collection<Set>) -> Unit, onFailure: (ClientException) -> Unit) {
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
        onSuccess: (Collection<Card>) -> Unit,
        onFailure: (ClientException) -> Unit
    ) {
        service
            .searchCards(query.toString())
            .executeListAndFold(
                {
                    onSuccess(it.map { item -> json.decodeFromJsonElement(item) })
                },
                onFailure = onFailure
            )
    }
}

fun createClient(): MtgClient {
    return MtgClientImpl(CallService.create())
}
