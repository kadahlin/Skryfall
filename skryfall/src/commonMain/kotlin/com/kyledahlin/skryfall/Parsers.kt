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
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

internal val clientJson = Json { ignoreUnknownKeys = true }

private val client = HttpClient {
    install(JsonFeature)
}

internal suspend inline fun <reified T> HttpResponse.parseSingleResponse(
    expectedObject: String
): ClientResponse<T> {
    return try {
        val body = receive<String>()
        if (status == HttpStatusCode.NotFound) {
            return ClientResponse.failure(UnknownResourceException())
        }
        val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
        responseMap.parseToExpected(expectedObject)
    } catch (e: Exception) {
        println("caught generic exc: $e")
        ClientResponse.failure(UnknownException())
    }
}

internal suspend inline fun <reified T> HttpResponse.parseList(
): ClientResponse<Collection<T>> {
//    ClientResponse.success(it.map { item -> json.decodeFromJsonElement(item) })
    if (status == HttpStatusCode.NotFound) {
        return ClientResponse.failure(UnknownResourceException())
    }
    val result = mutableListOf<JsonObject>()
    val body = receive<String>()
    val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
    try {
        if (!responseMap.hasExpectedType(CallService.Keys.LIST)) {
            return ClientResponse.failure(UnknownException("object was not of the expected type"))
        }

        result.addAll(responseMap[CallService.Keys.DATA]?.jsonArray?.map { it.jsonObject } ?: emptyList())
        var hasMore = responseMap[CallService.Keys.HAS_MORE]?.jsonPrimitive?.booleanOrNull
        var nextUrl = responseMap[CallService.Keys.NEXT_PAGE]?.jsonPrimitive?.content
        while (hasMore == true && nextUrl != null) {
            val nextResponse = client.get<HttpResponse>(nextUrl)
            val nextBody = nextResponse.content.toString()
            val decodedResponse = Json.decodeFromString(JsonObject.serializer(), nextBody)
            hasMore = decodedResponse[CallService.Keys.HAS_MORE]?.jsonPrimitive?.booleanOrNull
            nextUrl = decodedResponse[CallService.Keys.NEXT_PAGE]?.jsonPrimitive?.contentOrNull
            if (!decodedResponse.hasExpectedType(
                    CallService.Keys.LIST
                )
            ) {
                return ClientResponse.failure(UnknownException("subsequent call was not of the expected type"))
            }

            result.addAll(decodedResponse[CallService.Keys.DATA]?.jsonArray?.map { it.jsonObject }
                ?: emptyList())

        }
    } catch (e: Exception) {
        println("caught generic exc: $e")
        return ClientResponse.failure(UnknownException())
    }

    return ClientResponse.success(result.map { item -> clientJson.decodeFromJsonElement(item) })

}

private inline fun JsonObject.hasExpectedType(
    key: String
) = this.containsKey(CallService.Keys.OBJECT) && this[CallService.Keys.OBJECT]?.jsonPrimitive?.contentOrNull == key

private inline fun <reified T> JsonObject.parseToExpected(key: String): ClientResponse<T> =
    if (this.containsKey(CallService.Keys.OBJECT) && this[CallService.Keys.OBJECT]?.jsonPrimitive?.contentOrNull == key) ClientResponse.success(
        clientJson.decodeFromJsonElement(this)
    ) else ClientResponse.failure(SerializationException)

