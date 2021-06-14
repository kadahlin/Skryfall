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
package com.kyledahlin.skryfall.objects

import com.kyledahlin.skryfall.CardColorSerializer
import kotlinx.serialization.Serializable

@Serializable(with = CardColorSerializer::class)
enum class CardColor {
    WHITE, BLACK, RED, BLUE, GREEN, COLORLESS;

    val shorthand: String
        get() = when (this) {
            WHITE -> "w"
            BLACK -> "b"
            RED -> "r"
            BLUE -> "u"
            GREEN -> "g"
            COLORLESS -> "c"
        }
}
