package com.kyledahlin.scryfall.objects

import com.kyledahlin.scryfall.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Ruling(
    val source: String,
    @Serializable(with = DateSerializer::class) @SerialName("published_at") val publishedAt: Date,
    val comment: String
)