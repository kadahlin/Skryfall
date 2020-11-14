package com.kyledahlin.scryfall

import kotlinx.serialization.json.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Call<ResponseBody>.executeAndFold(
    expectedObject: String,
    onResponse: (JsonObject) -> Unit,
    onFailure: (ClientException) -> Unit
) {

    try {
        println("get set for code on thread: ${Thread.currentThread().name}")
        val response = execute()
        if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
            onFailure(UnknownResourceException())
        }
        val body = response.body()?.string() ?: "{}"
        val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
        responseMap.foldForExpected(expectedObject, {
            onFailure(UnknownException())
        }, {
            onResponse(responseMap)
        })
    } catch (e: Exception) {
        println("caught exc: $e")
        val exception = when (e) {
            is UnknownHostException -> ApiUnavailableException
            else -> UnknownException()
        }
        onFailure(exception)
    }
}

fun Call<ResponseBody>.executeListAndFold(
    onResponse: (List<JsonObject>) -> Unit,
    onFailure: (ClientException) -> Unit
) {

    try {
        val response = execute()
        val result = mutableListOf<JsonObject>()
        val body = response.body()?.string() ?: "{}"
        val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
        responseMap.foldForExpected(CallService.Keys.LIST, {
            onFailure(UnknownException())
        }, {
            result.addAll(responseMap[CallService.Keys.DATA]?.jsonArray?.map { it.jsonObject } ?: emptyList())
            var hasMore = responseMap[CallService.Keys.HAS_MORE]?.jsonPrimitive?.booleanOrNull
            while (hasMore == true) {
                val url = HttpUrl.get(responseMap[CallService.Keys.NEXT_PAGE]?.jsonPrimitive?.contentOrNull ?: "")
                val response = OkHttpClient().newCall(Request.Builder().url(url).get().build()).execute()
                val body = response.body()?.string() ?: "{}"
                val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
                hasMore = responseMap["has_more"]?.jsonPrimitive?.booleanOrNull
                responseMap.foldForExpected(CallService.Keys.LIST, {
                    result.addAll(responseMap[CallService.Keys.DATA]?.jsonArray?.map { it.jsonObject } ?: emptyList())
                }, {
                    onFailure(UnknownException())
                })
            }
            onResponse(result)
        })
    } catch (e: Exception) {
        println("caught exc: $e")
        val exception = when (e) {
            is UnknownHostException -> ApiUnavailableException
            else -> UnknownException()
        }
        onFailure(exception)
    }
}

private fun JsonObject.foldForExpected(key: String, onNotPresent: () -> Unit, onPresent: () -> Unit) {
    if (this.containsKey(CallService.Keys.OBJECT) && this[CallService.Keys.OBJECT]?.jsonPrimitive?.contentOrNull == key) onPresent() else onNotPresent()
}