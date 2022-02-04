package com.kyledahlin.skryfall.test

import com.kyledahlin.skryfall.objects.*
import kotlinx.serialization.json.JsonObject
import java.net.URI
import java.util.*

fun card(
    arenaId: Int? = null,
    id: UUID = UUID.randomUUID(),
    lang: String = "",
    mtgoId: Int? = null,
    mtgoFoilId: Int? = null,
    multiverseIds: List<Int>? = null,
    tcgPlayerId: Int? = null,
    cardMarketId: Int? = null,
    oracleId: UUID = UUID.randomUUID(),
    printsSearchUri: URI = URI.create(""),
    rulingsUri: URI = URI.create(""),
    scryfallUri: URI = URI.create(""),
    uri: URI = URI.create(""),
    artist: String? = null,
    booster: Boolean = false,
    borderColor: String = "",
    cardBackId: UUID = UUID.randomUUID(),
    collectorNumber: String = "",
    contentWarning: Boolean? = null,
    digital: Boolean = false,
    flavorName: String? = null,
    flavorText: String? = null,
    frameEffects: List<String>? = null,
    frame: String = "",
    fullArt: Boolean = false,
    games: List<String> = listOf(),
    highresImage: Boolean = false,
    illustrationId: UUID? = null,
    imageUris: JsonObject? = null,
    prices: JsonObject = JsonObject(emptyMap()),
    printedName: String? = null,
    printedText: String? = null,
    printedTypeLine: String? = null,
    promo: Boolean = false,
    promoTypes: List<String>? = null,
    purchaseUris: JsonObject = JsonObject(emptyMap()),
    rarity: String = "",
    relatedUris: JsonObject = JsonObject(emptyMap()),
    releasedAt: Date = Date(),
    reprint: Boolean = false,
    scryfallSetUri: URI = URI.create(""),
    setType: String = "",
    setUri: URI = URI.create(""),
    set: String = "",
    storySpotlight: Boolean = false,
    textless: Boolean = false,
    variation: Boolean = false,
    variationOf: UUID? = null,
    watermark: String? = null,
    preview: Preview? = null,
    allParts: List<RelatedCard>? = null,
    cardFaces: List<CardFace>? = null,
    cmc: Double = 0.0,
    colors: List<CardColor>? = null,
    colorIdentity: List<CardColor> = emptyList(),
    colorIndicator: List<CardColor>? = null,
    edhrecRank: Int? = null,
    foil: Boolean = false,
    handModifier: String? = null,
    keywords: List<String> = emptyList(),
    layout: String = "",
    legalities: JsonObject = JsonObject(emptyMap()),
    lifeModifier: String? = null,
    loyalty: String? = null,
    manaCost: String? = null,
    name: String = "",
    nonfoil: Boolean = false,
    oracleText: String? = null,
    oversized: Boolean = false,
    power: String? = null,
    producedMana: List<CardColor>? = null,
    reserved: Boolean = false,
    toughness: String? = null,
    typeLine: String = ""
): Card = Card(
    arenaId = arenaId,
    id = id,
    lang = lang,
    mtgoId = mtgoId,
    mtgoFoilId = mtgoFoilId,
    multiverseIds = multiverseIds,
    tcgPlayerId = tcgPlayerId,
    cardMarketId = cardMarketId,
    oracleId = oracleId,
    printsSearchUri = printsSearchUri,
    rulingsUri = rulingsUri,
    scryfallUri = scryfallUri,
    uri = uri,
    artist = artist,
    booster = booster,
    borderColor = borderColor,
    cardBackId = cardBackId,
    collectorNumber = collectorNumber,
    contentWarning = contentWarning,
    digital = digital,
    flavorName = flavorName,
    flavorText = flavorText,
    frameEffects = frameEffects,
    frame = frame,
    fullArt = fullArt,
    games = games,
    highresImage = highresImage,
    illustrationId = illustrationId,
    imageUris = imageUris,
    prices = prices,
    printedName = printedName,
    printedText = printedText,
    printedTypeLine = printedTypeLine,
    promo = promo,
    promoTypes = promoTypes,
    purchaseUris = purchaseUris,
    rarity = rarity,
    relatedUris = relatedUris,
    releasedAt = releasedAt,
    reprint = reprint,
    scryfallSetUri = scryfallSetUri,
    setType = setType,
    setUri = setUri,
    set = set,
    storySpotlight = storySpotlight,
    textless = textless,
    variation = variation,
    variationOf = variationOf,
    watermark = watermark,
    preview = preview,
    allParts = allParts,
    cardFaces = cardFaces,
    cmc = cmc,
    colors = colors,
    colorIdentity = colorIdentity,
    colorIndicator = colorIndicator,
    edhrecRank = edhrecRank,
    foil = foil,
    handModifier = handModifier,
    keywords = keywords,
    layout = layout,
    legalities = legalities,
    lifeModifier = lifeModifier,
    loyalty = loyalty,
    manaCost = manaCost,
    name = name,
    nonfoil = nonfoil,
    oracleText = oracleText,
    oversized = oversized,
    power = power,
    producedMana = producedMana,
    reserved = reserved,
    toughness = toughness,
    typeLine = typeLine
)

fun relatedCard(
    id: UUID = UUID.randomUUID(),
    component: Component = Component.TOKEN,
    name: String = "",
    typeLine: String = "",
    uri: URI = URI.create("")
): RelatedCard = RelatedCard(
    id = id, component = component, name = name, typeLine = typeLine, uri = uri
)

fun cardFace(
    artist: String? = null,
    colorIndicator: List<CardColor>? = null,
    colors: List<CardColor>? = null,
    flavorText: String? = null,
    illustrationId: UUID? = null,
    imageUris: JsonObject? = null,
    loyalty: String? = null,
    manaCost: String = "",
    name: String = "",
    oracleText: String? = null,
    power: String? = null,
    printedText: String? = null,
    printedName: String? = null,
    printedTypeLine: String? = null,
    toughness: String? = null,
    typeLine: String = "",
    watermark: String? = null
): CardFace = CardFace(
    artist = artist,
    colorIndicator = colorIndicator,
    colors = colors,
    flavorText = flavorText,
    illustrationId = illustrationId,
    imageUris = imageUris,
    loyalty = loyalty,
    manaCost = manaCost,
    name = name,
    oracleText = oracleText,
    power = power,
    printedText = printedText,
    printedName = printedName,
    printedTypeLine = printedTypeLine,
    toughness = toughness,
    typeLine = typeLine,
    watermark = watermark
)