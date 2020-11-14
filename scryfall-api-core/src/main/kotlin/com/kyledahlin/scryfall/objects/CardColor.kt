package com.kyledahlin.scryfall.objects

import com.kyledahlin.scryfall.CardColorSerializer
import kotlinx.serialization.Serializable

@Serializable(with = CardColorSerializer::class)
enum class CardColor {
    WHITE, BLACK, RED, BLUE, GREEN
}