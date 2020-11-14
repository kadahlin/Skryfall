package com.kyledahlin.scryfall.coroutines

import com.kyledahlin.scryfall.CallService
import com.kyledahlin.scryfall.ClientException
import com.kyledahlin.scryfall.executeAndFold
import com.kyledahlin.scryfall.executeListAndFold
import com.kyledahlin.scryfall.objects.CardSymbol
import com.kyledahlin.scryfall.objects.ManaCost
import com.kyledahlin.scryfall.objects.Ruling
import com.kyledahlin.scryfall.objects.Set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.ResponseBody
import retrofit2.Call
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface CoroutineClient {
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
}

class CoroutineClientImpl(
    private val service: CallService, private val scope: CoroutineScope
) : CoroutineClient {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getSetWithCode(code: String): ClientResponse<Set> =
        service
            .getSetWithCode(code)
            .suspendToResponse(CallService.Keys.SET)

    override suspend fun getSetWithId(id: String): ClientResponse<Set> =
        service
            .getSetWithId(id)
            .suspendToResponse(CallService.Keys.SET)

    override suspend fun getSetWithTcgPlayerId(id: String): ClientResponse<Set> =
        service
            .getSetWithTcgPlayerId(id)
            .suspendToResponse(CallService.Keys.SET)

    override suspend fun getSets(): ClientResponse<Collection<Set>> =
        service
            .getSets()
            .suspendListToResponse()

    override suspend fun getRulingsForMtgoId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForMtgoId(id)
            .suspendListToResponse()

    override suspend fun getRulingsForArenaId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForArenaId(id)
            .suspendListToResponse()

    override suspend fun getRulingsForMultiverseId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForMultiverseId(id)
            .suspendListToResponse()

    override suspend fun getRulingsForCardId(id: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForCardId(id)
            .suspendListToResponse()

    override suspend fun getRulingsForCodeAndNumber(code: String, number: String): ClientResponse<Collection<Ruling>> =
        service
            .getRulingsForCodeAndNumber(code, number)
            .suspendListToResponse()

    override suspend fun getAllSymbols(): ClientResponse<Collection<CardSymbol>> =
        service
            .getAllSymbols()
            .suspendListToResponse()

    override suspend fun getManaCostForSymbols(symbols: List<CardSymbol>): ClientResponse<ManaCost> =
        service
            .getManaCostForSymbols(symbols.joinToString("") { it.symbol })
            .suspendToResponse(CallService.Keys.MANA_COST)

    private suspend inline fun <reified T> Call<ResponseBody>.suspendToResponse(
        expectedObject: String,
    ): ClientResponse<T> {
        return suspendCoroutine { cont ->
            scope.launch {
                executeAndFold(expectedObject, {
                    cont.resume(ClientResponse.success(json.decodeFromJsonElement(it)))
                }, {
                    cont.resume(ClientResponse.failure(it))
                })
            }
        }
    }

    private suspend inline fun <reified T> Call<ResponseBody>.suspendListToResponse(): ClientResponse<Collection<T>> {
        return suspendCoroutine { cont ->
            scope.launch {
                executeListAndFold({
                    cont.resume(ClientResponse.success(it.map { item -> json.decodeFromJsonElement(item) }))
                }, {
                    cont.resume(ClientResponse.failure(it))
                })
            }
        }
    }
}

sealed class ClientResponse<T> {
    companion object {
        fun <T> success(t: T) = Success(t)
        fun <T> failure(e: ClientException) = Failure<T>(e)
    }
}

data class Success<T>(val data: T) : ClientResponse<T>()
data class Failure<T>(val exception: ClientException) : ClientResponse<T>()

fun createClient(scope: CoroutineScope? = null): CoroutineClient {
    return CoroutineClientImpl(CallService.create(), scope = scope ?: CoroutineScope(Dispatchers.IO))
}