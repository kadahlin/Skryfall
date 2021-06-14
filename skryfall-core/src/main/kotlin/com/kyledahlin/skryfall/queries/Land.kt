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
package com.kyledahlin.skryfall.queries

import com.kyledahlin.skryfall.negatableIsQuery

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
