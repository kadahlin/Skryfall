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

import com.kyledahlin.skryfall.CardQuery
import com.kyledahlin.skryfall.RawQuery

val Loyalty
    get() = NumericQueryBuilder("loy")

object Power : NumericQueryBuilder("pow") {
    val isEqualToughness: CardQuery = RawQuery("pow=tou")
    val isNotEqualToughness: CardQuery = RawQuery("pow!=tou")
    val isLessThanToughness: CardQuery = RawQuery("pow<tou")
    val isLessThanOrEqualToughness: CardQuery = RawQuery("pow<=tou")
    val isGreaterThanToughness: CardQuery = RawQuery("pow>tou")
    val isGreaterThanOrEqualToToughness: CardQuery = RawQuery("pow>=tou")
}

object Toughness : NumericQueryBuilder("tou") {
    val isEqualPower: CardQuery = RawQuery("tou=pow")
    val isNotEqualPower: CardQuery = RawQuery("tou!=pow")
    val isLessThanPower: CardQuery = RawQuery("tou<pow")
    val isLessThanOrEqualPower: CardQuery = RawQuery("tou<=pow")
    val isGreaterThanPower: CardQuery = RawQuery("tou>pow")
    val isGreaterThanOrEqualToPower: CardQuery = RawQuery("tou>=pow")
}
