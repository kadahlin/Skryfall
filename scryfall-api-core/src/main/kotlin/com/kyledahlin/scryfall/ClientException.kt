package com.kyledahlin.scryfall

sealed class ClientException
data class UnknownResourceException(val resource: Any? = null) : ClientException()
data class UnknownException(val reason: String? = null) : ClientException()
object ApiUnavailableException : ClientException()
