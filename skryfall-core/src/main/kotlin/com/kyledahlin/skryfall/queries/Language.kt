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
import com.kyledahlin.skryfall.NegatableQuery
import com.kyledahlin.skryfall.RawQuery

object Language {
    // TODO: can probably find some type safe values for this
    fun matches(value: String) = NegatableQuery("lang:$value")

    val firstPrinting: CardQuery get() = RawQuery("new:language")

    fun printedIn(value: String) = NegatableQuery("in:$value")
}
