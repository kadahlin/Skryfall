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

import com.kyledahlin.skryfall.ComponentSerializer
import com.kyledahlin.skryfall.DateSerializer
import com.kyledahlin.skryfall.UUIDSerializer
import com.kyledahlin.skryfall.UriSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.URI
import java.util.*

private const val NORMAL_IMAGE_KEY = "normal"

@Serializable
data class Card(
    @SerialName("arena_id") val arenaId: Int? = null,
    @Serializable(UUIDSerializer::class) val id: UUID,
    val lang: String,
    @SerialName("mtgo_id") val mtgoId: Int? = null,
    @SerialName("mtgo_foil_id") val mtgoFoilId: Int? = null,
    @SerialName("multiverse_ids") val multiverseIds: List<Int>? = null,
    @SerialName("tcgplayer_id") val tcgPlayerId: Int? = null,
    @SerialName("card_market_id") val cardMarketId: Int? = null,
    @SerialName("oracle_id") @Serializable(UUIDSerializer::class) val oracleId: UUID,
    @SerialName("prints_search_uri") @Serializable(UriSerializer::class) val printsSearchUri: URI,
    @SerialName("rulings_uri") @Serializable(UriSerializer::class) val rulingsUri: URI,
    @SerialName("scryfall_uri") @Serializable(UriSerializer::class) val scryfallUri: URI,
    @Serializable(UriSerializer::class) val uri: URI,

    // print fields
    val artist: String? = null,
    val booster: Boolean,
    @SerialName("border_color") val borderColor: String,
    @SerialName("card_back_id") @Serializable(UUIDSerializer::class) val cardBackId: UUID,
    @SerialName("collector_number") val collectorNumber: String,
    @SerialName("content_warning") val contentWarning: Boolean? = null,
    val digital: Boolean,
    @SerialName("flavor_name") val flavorName: String? = null,
    @SerialName("flavor_text") val flavorText: String? = null,
    @SerialName("frame_effects") val frameEffects: List<String>? = null,
    val frame: String,
    @SerialName("full_art") val fullArt: Boolean,
    val games: List<String>,
    @SerialName("highres_image") val highresImage: Boolean,
    @SerialName("illustration_id") @Serializable(UUIDSerializer::class) val illustrationId: UUID? = null,
    @SerialName("image_uris") val imageUris: JsonObject? = null,
    val prices: JsonObject,
    @SerialName("printed_name") val printedName: String? = null,
    @SerialName("printed_text") val printedText: String? = null,
    @SerialName("printed_type_line") val printedTypeLine: String? = null,
    val promo: Boolean,
    @SerialName("promo_types") val promoTypes: List<String>? = null,
    @SerialName("purchase_uris") val purchaseUris: JsonObject,
    val rarity: String,
    @SerialName("related_uris") val relatedUris: JsonObject,
    @SerialName("released_at") @Serializable(DateSerializer::class) val releasedAt: Date,
    val reprint: Boolean,
    @SerialName("scryfall_set_uri") @Serializable(UriSerializer::class) val scryfallSetUri: URI,
    @SerialName("set_type") val setType: String,
    @SerialName("set_uri") @Serializable(UriSerializer::class) val setUri: URI,
    val set: String,
    @SerialName("story_spotlight") val storySpotlight: Boolean,
    val textless: Boolean,
    val variation: Boolean,
    @SerialName("variation_of") @Serializable(UUIDSerializer::class) val variationOf: UUID? = null,
    val watermark: String? = null,
    val preview: Preview? = null,

    // game play fields
    @SerialName("all_parts") val allParts: List<RelatedCard>? = null,
    @SerialName("card_faces") val cardFaces: List<CardFace>? = null,
    val cmc: Double,
    val colors: List<CardColor>? = null,
    @SerialName("color_identity") val colorIdentity: List<CardColor>,
    @SerialName("color_indicator") val colorIndicator: List<CardColor>? = null,
    @SerialName("edhrec_rank") val edhrecRank: Int? = null,
    val foil: Boolean,
    @SerialName("hand_modifier") val handModifier: String? = null,
    val keywords: List<String>,
    val layout: String,
    val legalities: JsonObject,
    @SerialName("life_modifier") val lifeModifier: String? = null,
    val loyalty: String? = null,
    @SerialName("mana_cost") val manaCost: String? = null,
    val name: String,
    val nonfoil: Boolean,
    @SerialName("oracle_text") val oracleText: String? = null,
    val oversized: Boolean,
    val power: String? = null,
    @SerialName("produced_mana") val producedMana: List<CardColor>? = null,
    val reserved: Boolean,
    val toughness: String? = null,
    @SerialName("type_line") val typeLine: String
) {
    fun getImageUris(format: ImageFormat, includeFaces: Boolean = true): List<String> {
        val stringFormat = format.format
        val result = mutableListOf<String>()
        imageUris?.get(stringFormat)?.let { result.add(it.jsonPrimitive.content) }

        if (includeFaces) {
            cardFaces?.forEach { face ->
                face.imageUris?.get(stringFormat)?.let { result.add(it.jsonPrimitive.content) }
            }
        }
        return result
    }

    enum class ImageFormat(val format: String) {
        PNG("png"), BORDER_CROP("border_crop"), ART_CROP("art_crop"), LARGE("large"), NORMAL("normal"), SMALL("small")
    }
}

@Serializable
data class Preview(
    @SerialName("previewed_at") @Serializable(DateSerializer::class) val previewedAt: Date? = null,
    @SerialName("source_uri") @Serializable(UriSerializer::class) val sourceUri: URI? = null,
    val source: String? = null
)

@Serializable
data class CardFace(
    val artist: String? = null,
    @SerialName("color_indicator") val colorIndicator: List<CardColor>? = null,
    val colors: List<CardColor>? = null,
    @SerialName("flavor_text") val flavorText: String? = null,
    @SerialName("illustration_id") @Serializable(with = UUIDSerializer::class) val illustrationId: UUID? = null,
    @SerialName("image_uris") val imageUris: JsonObject? = null,
    val loyalty: String? = null,
    @SerialName("mana_cost") val manaCost: String,
    val name: String,
    @SerialName("oracle_text") val oracleText: String? = null,
    val power: String? = null,
    @SerialName("printed_name") val printedName: String? = null,
    @SerialName("printed_text") val printedText: String? = null,
    @SerialName("printed_type_line") val printedTypeLine: String? = null,
    val toughness: String? = null,
    @SerialName("type_line") val typeLine: String,
    val watermark: String? = null
)

@Serializable
data class RelatedCard(
    @Serializable(UUIDSerializer::class) val id: UUID,
    val component: Component,
    val name: String,
    @SerialName("type_line") val typeLine: String,
    @Serializable(UriSerializer::class) val uri: URI
)

@Serializable(ComponentSerializer::class)
enum class Component {
    TOKEN, MELD_PART, MELD_RESULT, COMBO_PIECE
}
