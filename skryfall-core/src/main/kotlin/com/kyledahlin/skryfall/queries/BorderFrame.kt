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

object Border {
    val white
        get(): CardQuery = RawQuery("border:white")

    val black
        get(): CardQuery = RawQuery("border:black")

    val silver
        get(): CardQuery = RawQuery("border:silver")

    val borderless
        get(): CardQuery = RawQuery("border:borderless")
}

object Frame {
    val is1993
        get(): CardQuery = RawQuery("frame:1993")

    val is1997
        get(): CardQuery = RawQuery("frame:1997")

    val is2003
        get(): CardQuery = RawQuery("frame:2003")

    val is2015
        get(): CardQuery = RawQuery("frame:2015")

    val isFuture
        get(): CardQuery = RawQuery("frame:future")

    val isLegendary
        get(): CardQuery = RawQuery("frame:legendary")

    val isColorshifted
        get(): CardQuery = RawQuery("frame:colorshifted")

    val isTombstone
        get(): CardQuery = RawQuery("frame:tombstone")

    val isNyxtouched
        get(): CardQuery = RawQuery("frame:nyxtouched")

    val isNew
        get():CardQuery = RawQuery("new:frame")

    val isOld
        get(): CardQuery = RawQuery("is:old")


}