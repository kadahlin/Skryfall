package com.kyledahlin.scryfall.objects

import com.kyledahlin.scryfall.UriSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URI

@Serializable
data class CardSymbol(
    val symbol: String,
    @SerialName("loose_variant") val looseVariant: String? = null,
    val english: String,
    val transposable: Boolean,
    @SerialName("represents_mana") val representsMana: Boolean,
    val cmc: Double? = null,
    @SerialName("appears_in_mana_costs") val appearsInManaCosts: Boolean,
    val funny: Boolean,
    val colors: List<CardColor>,
    @SerialName("gatherer_alternates") val gathererAlternates: List<String>? = null,
    @SerialName("svg_uri") @Serializable(with = UriSerializer::class) val svgUri: URI? = null
)