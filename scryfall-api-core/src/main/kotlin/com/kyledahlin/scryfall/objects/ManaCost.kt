package com.kyledahlin.scryfall.objects

import kotlinx.serialization.Serializable

@Serializable
data class ManaCost(
    val cmc: Double,
    val cost: String,
    val colors: List<CardColor>,
    val colorless: Boolean,
    val monocolored: Boolean,
    val multicolored: Boolean
)