package com.kyledahlin.scryfall.objects

import com.kyledahlin.scryfall.UriSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URI

/**
 * Information about an set of cards. This could be either an official set (legal in standard play) or an unofficial set (promotional)
 */
@Serializable
data class Set(
    val id: String,
    val code: String,
    @SerialName("mtgo_code") val mtgoCode: String? = null,
    @SerialName("tcgplayer_id") val tcgplayerId: Int? = null,
    val name: String,
    @SerialName("set_type") val setType: String,
    @SerialName("released_at") val releasedAt: String? = null,
    @SerialName("block_code") val blockCode: String? = null,
    val block: String? = null,
    @SerialName("parent_set_code") val parentSetCode: String? = null,
    @SerialName("card_count") val cardCount: Int,
    @SerialName("printed_size") val printedSize: Int? = null,
    val digital: Boolean,
    @SerialName("foil_only") val foilOnly: Boolean,
    @SerialName("nonfoil_only") val nonFoilOnly: Boolean,
    @SerialName("scryfall_uri") @Serializable(with = UriSerializer::class) val scryfallUri: URI,
    @Serializable(with = UriSerializer::class) val uri: URI,
    @SerialName("icon_svg_uri") @Serializable(with = UriSerializer::class) val iconSvgUri: URI,
    @SerialName("search_uri") @Serializable(with = UriSerializer::class) val searchUri: URI
)