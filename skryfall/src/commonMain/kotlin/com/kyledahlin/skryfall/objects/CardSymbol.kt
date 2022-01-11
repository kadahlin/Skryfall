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
package com.kyledahlin.skryfall.objects

import com.kyledahlin.skryfall.UriSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    @SerialName("svg_uri") @Serializable(with = UriSerializer::class) val svgUri: Uri? = null
)
