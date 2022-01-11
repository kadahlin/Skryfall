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

import com.kyledahlin.skryfall.objects.CardColor
import com.kyledahlin.skryfall.objects.Component
import com.kyledahlin.skryfall.objects.Uri
import com.kyledahlin.skryfall.objects.Uuid
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object DateSerializer : KSerializer<LocalDate> {

    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): LocalDate = decoder.decodeString().toLocalDate()
    override fun serialize(encoder: Encoder, value: LocalDate) = encoder.encodeString(value.toString())
}

internal object UriSerializer : KSerializer<Uri> {
    override val descriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Uri = Uri(
        decoder.decodeString().trim()
    ) // trim() is used here since I have come across at least one URI returned from the api with a trailing space

    override fun serialize(encoder: Encoder, value: Uri) = encoder.encodeString(value.value)
}

internal object CardColorSerializer : KSerializer<CardColor> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CardColor", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = when (val color = decoder.decodeString()) {
        "W" -> CardColor.WHITE
        "B" -> CardColor.BLACK
        "R" -> CardColor.RED
        "U" -> CardColor.BLUE
        "G" -> CardColor.GREEN
        "C" -> CardColor.COLORLESS
        else -> throw Exception("unknown value when deserializing colors: $color")
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

internal object UuidSerializer : KSerializer<Uuid> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder) = Uuid(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Uuid) = encoder.encodeString(value.value)
}

internal object ComponentSerializer : KSerializer<Component> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Component", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = when (val comp = decoder.decodeString()) {
        "token" -> Component.TOKEN
        "meld_part" -> Component.MELD_PART
        "meld_result" -> Component.MELD_RESULT
        "combo_piece" -> Component.COMBO_PIECE
        else -> throw Exception("unknown value when deserializing component: $comp")
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
