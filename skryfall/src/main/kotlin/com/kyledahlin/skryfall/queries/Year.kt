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

val Year = NumericQueryBuilder("year")

object Date {
    fun equals(year: Int, month: Int, day: Int): CardQuery = RawQuery("date=${formatDate(year, month, day)}")
    fun doesNotEqual(year: Int, month: Int, day: Int): CardQuery = RawQuery("date!=${formatDate(year, month, day)}")
    fun lessThan(year: Int, month: Int, day: Int): CardQuery = RawQuery("date<${formatDate(year, month, day)}")
    fun lessThanOrEqualTo(year: Int, month: Int, day: Int): CardQuery =
        RawQuery("date<=${formatDate(year, month, day)}")

    fun greaterThan(year: Int, month: Int, day: Int): CardQuery = RawQuery("date>${formatDate(year, month, day)}")
    fun greaterThanOrEqualTo(year: Int, month: Int, day: Int): CardQuery =
        RawQuery("date>=${formatDate(year, month, day)}")

    fun duringSet(code: String): CardQuery = RawQuery("date=$code")
    fun notDuringSet(code: String): CardQuery = RawQuery("date!=$code")
    fun beforeSet(code: String): CardQuery = RawQuery("date<$code")
    fun beforeOrDuringSet(code: String): CardQuery = RawQuery("date<=$code")
    fun afterSet(code: String): CardQuery = RawQuery("date>$code")
    fun afterOrDuringSet(code: String): CardQuery =
        RawQuery("date>=$code")

    private fun formatDate(year: Int, month: Int, day: Int): String {
        return "$year-$month-$day"
    }
}