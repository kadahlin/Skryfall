package com.kyledahlin.scryfall.queries

import com.kyledahlin.scryfall.negatableIsQuery

/**
 * Land nicknames that are recognized by the search api
 */
object Land {
    val isBikeLand = negatableIsQuery("bikeland")
    val isCycleLand = isBikeLand
    val isBicycleLand = isBikeLand

    val isBounceLand = negatableIsQuery("bounceland")
    val isKarooLand = isBounceLand
    val isCanopyLand = negatableIsQuery("canopyland")
    val isCanLand = isCanopyLand

    val isCheckLand = negatableIsQuery("checkland")
    val isDual = negatableIsQuery("dual")
    val isFastLand = negatableIsQuery("fastland")
    val isFetchLand = negatableIsQuery("fetchland")
    val isFilterLand = negatableIsQuery("filterland")
    val isGainLand = negatableIsQuery("gainland")
    val isPainLand = negatableIsQuery("painland")
    val isScryLand = negatableIsQuery("scryland")
    val isShadowLand = negatableIsQuery("shadowland")
    val isShockLand = negatableIsQuery("shockland")
    val isStorageLand = negatableIsQuery("storageland")
    val isCreatureLand = negatableIsQuery("createland")
    val isTriland = negatableIsQuery("triland")
    val isTangoLand = negatableIsQuery("tangoland")
    val isBattleLand = isTangoLand
}
