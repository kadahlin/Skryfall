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

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

internal const val ENDPOINT = "https://api.scryfall.com"

/**
 * Client for interacting with the scryfall api and fetching data about magic cards
 */
interface CallService {
    // ---------------------------------
    // Sets
    // ---------------------------------
    @GET("/sets/{code}") // set with this exact code
    fun getSetWithCode(@Path("code") code: String): Call<ResponseBody>

    @GET("/sets/{id}") // set with this exact id
    fun getSetWithId(@Path("id") id: String): Call<ResponseBody>

    @GET("/sets/tcgplayer/{id}") // set with this exact id
    fun getSetWithTcgPlayerId(@Path("id") id: String): Call<ResponseBody>

    // all sets
    @GET("/sets")
    fun getSets(): Call<ResponseBody>

    // ---------------------------------
    // Rulings
    // ---------------------------------
    @GET("/cards/multiverse/{id}/rulings")
    fun getRulingsForMultiverseId(@Path("id") id: String): Call<ResponseBody>

    @GET("/cards/mtgo/{id}/rulings")
    fun getRulingsForMtgoId(@Path("id") id: String): Call<ResponseBody>

    @GET("/cards/arena/{id}/rulings")
    fun getRulingsForArenaId(@Path("id") id: String): Call<ResponseBody>

    @GET("/cards/multiverse/{code}/{number}/rulings")
    fun getRulingsForCodeAndNumber(@Path("id") code: String, @Path("number") number: String): Call<ResponseBody>

    @GET("/cards/{id}/rulings")
    fun getRulingsForCardId(@Path("id") id: String): Call<ResponseBody>

    // ---------------------------------
    // Symbols
    // ---------------------------------
    @GET("/symbology")
    fun getAllSymbols(): Call<ResponseBody>

    @GET("/symbology/parse-mana")
    fun getManaCostForSymbols(@Query("cost") cost: String): Call<ResponseBody>

    // ---------------------------------
    // Cards
    // ---------------------------------
    @GET("/cards/search")
    fun queryCards(
        @Query("q") name: String,
        @Query("unique") unique: String? = null,
        @Query("order") order: String? = null,
        @Query("dir") dir: String? = null,
        @Query("include_extras") includeExtras: Boolean? = null,
        @Query("include_multilingual") includeMultilingual: Boolean? = null,
        @Query("include_variations") includeVariations: Boolean? = null,
        @Query("page") page: Int = 1,
    ): Call<ResponseBody>

    @GET
    fun getCards(@Url url: String): Call<ResponseBody>

    @GET("/cards/{id}")
    fun getCardWithScryfallId(
        @Path("id") name: String,
        @Query("format") format: String? = null,
        @Query("face") face: String? = null,
        @Query("version") version: String? = null,
    ): Call<ResponseBody>

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
        fun create(): CallService = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .build().create(CallService::class.java)
    }
}
