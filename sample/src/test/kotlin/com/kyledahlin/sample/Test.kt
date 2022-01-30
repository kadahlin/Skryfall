package com.kyledahlin.sample

import com.kyledahlin.skryfall.objects.Card
import com.kyledahlin.skryfall.test.card
import kotlinx.coroutines.runBlocking
import org.junit.Test

interface SomeRepo {
    suspend fun getCard(): Card
}

class ExampleTest {
    @Test
    fun `Repo should return the card`() = runBlocking {
        val mockRepo = object : SomeRepo {
            override suspend fun getCard(): Card {
                return card(name = "Krenko")
            }
        }
        assert(mockRepo.getCard().name == "Krenko")
    }
}