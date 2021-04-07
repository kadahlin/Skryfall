package com.kyledahlin.scryfall

import com.kyledahlin.scryfall.objects.CardColor
import com.kyledahlin.scryfall.objects.Component
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URI
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

internal object DateSerializer : KSerializer<Date> {
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    override val descriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Date = df.parse(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeString(df.format(value))
}

object UriSerializer : KSerializer<URI> {
    override val descriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): URI = URI.create(
        decoder.decodeString().trim()
    ) // trim() is used here since I have come across at least one URI returned from the api with a trailing space

    override fun serialize(encoder: Encoder, value: URI) = encoder.encodeString(value.toString())
}

object CardColorSerializer : KSerializer<CardColor> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CardColor", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = when (val color = decoder.decodeString()) {
        "W" -> CardColor.WHITE
        "B" -> CardColor.BLACK
        "R" -> CardColor.RED
        "U" -> CardColor.BLUE
        "G" -> CardColor.GREEN
        "C" -> CardColor.COLORLESS
        else -> throw NoSuchFieldException("unknown value when deserializing colors: $color")
    }

    override fun serialize(encoder: Encoder, value: CardColor) {
        val colorString = when (value) {
            CardColor.WHITE -> "W"
            CardColor.BLACK -> "B"
            CardColor.RED -> "R"
            CardColor.BLUE -> "U"
            CardColor.GREEN -> "G"
            CardColor.COLORLESS -> "C"
        }
        encoder.encodeString(colorString)
    }
}

object UUIDSerialiaer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder) = UUID.fromString(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())
}

object ComponentSerializer : KSerializer<Component> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Component", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = when (val comp = decoder.decodeString()) {
        "token" -> Component.TOKEN
        "meld_part" -> Component.MELD_PART
        "meld_result" -> Component.MELD_RESULT
        "combo_piece" -> Component.COMBO_PIECE
        else -> throw NoSuchFieldException("unknown value when deserializing component: $comp")
    }

    override fun serialize(encoder: Encoder, value: Component) {
        val colorString = when (value) {
            Component.TOKEN -> "token"
            Component.MELD_PART -> "meld_part"
            Component.MELD_RESULT -> "meld_result"
            Component.COMBO_PIECE -> "combo_piece"
        }
        encoder.encodeString(colorString)
    }
}
