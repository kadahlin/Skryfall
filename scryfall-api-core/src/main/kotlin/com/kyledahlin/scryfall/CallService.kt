package com.kyledahlin.scryfall

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val ENDPOINT = "https://api.scryfall.com"

/**
 * Client for interacting with the scryfall api and fetching data about magic cards
 */
interface CallService {
    //---------------------------------
    //Sets
    //---------------------------------
    @GET("/sets/{code}") //set with this exact code
    fun getSetWithCode(@Path("code") code: String): Call<ResponseBody>

    @GET("/sets/{id}") //set with this exact id
    fun getSetWithId(@Path("id") id: String): Call<ResponseBody>

    @GET("/sets/tcgplayer/{id}") //set with this exact id
    fun getSetWithTcgPlayerId(@Path("id") id: String): Call<ResponseBody>

    //all sets
    @GET("/sets")
    fun getSets(): Call<ResponseBody>

    //---------------------------------
    //Rulings
    //---------------------------------
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

    //---------------------------------
    //Symbols
    //---------------------------------
    @GET("/symbology")
    fun getAllSymbols(): Call<ResponseBody>

    @GET("/symbology/parse-mana")
    fun getManaCostForSymbols(@Query("cost") cost: String): Call<ResponseBody>

    object Keys {
        const val SET = "set"
        const val DATA = "data"
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