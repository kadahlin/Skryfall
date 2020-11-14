package com.kyledahlin.scryfall

import com.kyledahlin.scryfall.objects.CardColor
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
    override fun deserialize(decoder: Decoder): Date {
        return df.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(df.format(value))
    }
}

object UriSerializer : KSerializer<URI> {
    override val descriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): URI = URI.create(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: URI) = encoder.encodeString(value.toString())
}

object CardColorSerializer : KSerializer<CardColor> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("CardColor", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = when (decoder.decodeString()) {
        "W" -> CardColor.WHITE
        "B" -> CardColor.BLACK
        "R" -> CardColor.RED
        "U" -> CardColor.BLUE
        "G" -> CardColor.GREEN
        else -> throw NoSuchFieldException("unknown value when deserializing colors")
    }

    override fun serialize(encoder: Encoder, value: CardColor) {
        val colorString = when (value) {
            CardColor.WHITE -> "W"
            CardColor.BLACK -> "B"
            CardColor.RED -> "R"
            CardColor.BLUE -> "U"
            CardColor.GREEN -> "G"
        }
        encoder.encodeString(colorString)
    }
}