package com.kyledahlin.scryfall

import kotlinx.serialization.json.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Url
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Call<ResponseBody>.executeAndFold(
    expectedObject: String,
    onResponse: (JsonObject) -> Unit,
    onFailure: (ClientException) -> Unit
) {

    try {
        val response = execute()
        if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
            onFailure(UnknownResourceException())
            return
        }
        val body = response.body()?.string() ?: "{}"
        val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
        responseMap.foldForExpected(
            expectedObject,
            {
                onFailure(UnknownException())
            },
            {
                onResponse(responseMap)
            }
        )
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
    onFailure: (ClientException) -> Unit,
) {
    try {
        val response = execute()
        if (response.code() == HttpURLConnection.HTTP_NOT_FOUND) {
            onFailure(UnknownResourceException())
            return
        }
        val result = mutableListOf<JsonObject>()
        val body = response.body()?.string() ?: "{}"
        val responseMap = Json.decodeFromString(JsonObject.serializer(), body)
        responseMap.foldForExpected(
            CallService.Keys.LIST,
            {
                onFailure(UnknownException("object was not of the expected type"))
            },
            {
                result.addAll(responseMap[CallService.Keys.DATA]?.jsonArray?.map { it.jsonObject } ?: emptyList())
                var hasMore = responseMap[CallService.Keys.HAS_MORE]?.jsonPrimitive?.booleanOrNull
                var nextUrl = responseMap[CallService.Keys.NEXT_PAGE]?.jsonPrimitive?.content
                while (hasMore == true && nextUrl != null) {
                    val url = HttpUrl.get(nextUrl)
                    val nextResponse = OkHttpClient().newCall(Request.Builder().url(url).get().build()).execute()
                    val nextBody = nextResponse.body()?.string() ?: "{}"
                    val decodedResponse = Json.decodeFromString(JsonObject.serializer(), nextBody)
                    hasMore = decodedResponse[CallService.Keys.HAS_MORE]?.jsonPrimitive?.booleanOrNull
                    nextUrl = decodedResponse[CallService.Keys.NEXT_PAGE]?.jsonPrimitive?.contentOrNull
                    decodedResponse.foldForExpected(
                        CallService.Keys.LIST,
                        {
                            onFailure(UnknownException("subsequent call was not of the expected type"))
                        },
                        {
                            result.addAll(decodedResponse[CallService.Keys.DATA]?.jsonArray?.map { it.jsonObject }
                                ?: emptyList())
                        }
                    )
                }
                onResponse(result)
            }
        )
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
