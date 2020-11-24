package com.kyledahlin.scryfall.queries

object ManaCost {

}

enum class ManaSymbol(val raw: String) {
    WHITE("{W}"),
    BLACK("{B}"),
    BLUE("{U}"),
    GREEN("{G}"),
    RED("{R}"),
    SNOW("{S}"),
    COLORLESS("{C}"),
    TWO_OR_WHITE("{2/W}"),
    TWO_OR_BLACK("{2/B}"),
    TWO_OR_GREEN("{2/G}"),
    TWO_OR_BLUE("{2/U}"),
    TWO_OR_RED("{2/R}"),

    WHITE_OR_BLACK("{W/B}"),
    WHITE_OR_BLUE("{W/U}"),
    WHITE_OR_GREEN("{W/G}"),
    WHITE_OR_RED("{W/R}"),
    GREEN_OR_RED("{G/R}"),
    GREEN_OR_BLACK("{G/B}"),
    GREEN_OR_BLUE("{G/U}"),
    RED_OR_BLUE("{R/U}"),
    RED_OR_BLACK("{R/B}"),
    BLUE_OR_BLACK("{U/B}"),



}

