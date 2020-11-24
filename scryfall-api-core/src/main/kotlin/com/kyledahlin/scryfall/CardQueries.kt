package com.kyledahlin.scryfall

object CardQueries {
    val isMasterpiece = negatableIsQuery("masterpiece")
    val isColorshifted = negatableIsQuery("colorshifted")

    val isSplit = negatableIsQuery("split")
    val isFlip = negatableIsQuery("flip")
    val isTransform = negatableIsQuery("transform")
    val isMeld = negatableIsQuery("meld")
    val isLeveler = negatableIsQuery("leveler")

    val isParty = negatableIsQuery("party")
    val isModal = negatableIsQuery("modal")
    val isPermanent = negatableIsQuery("permanent")
    val isHistoric = negatableIsQuery("historic")
    val isVanilla = negatableIsQuery("vanilla")
    val isFrenchVanilla = negatableIsQuery("frenchvanilla")
    val isFunny = negatableIsQuery("funny")

    val isSpell = negatableIsQuery("spell")
    val isDateStamped = negatableIsQuery("datestamped")
    val isRelease = negatableIsQuery("release")
    val isPreRelease = negatableIsQuery("prerelease")
    val isBooster = negatableIsQuery("booster")
    val isInPlanesWalkerDeck = negatableIsQuery("planeswalker_deck")

    val isLeague = negatableIsQuery("league")
    val isBuyABox = negatableIsQuery("buyabox")
    val isGiftBox = negatableIsQuery("giftbox")
    val isIntroPack = negatableIsQuery("isIntroPack")
    val isGameDay = negatableIsQuery("gameday")
    val isReprint = negatableIsQuery("reprint")

    val isFoil = negatableIsQuery("foil")
    val isNonFoil = negatableIsQuery("nonfoil")
    val isHighRes = negatableIsQuery("hires")

    val isPromo = negatableIsQuery("promo")
    val isSpotlight = negatableIsQuery("spotlight")
    val wasNeverReprinted = negatableIsQuery("papersets=1")

    val isPhyrexian = negatableIsQuery("phyrexian")
    val isHybrid = negatableIsQuery("hybrid")


}
