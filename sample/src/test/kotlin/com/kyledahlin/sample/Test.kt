import com.kyledahlin.skryfall.objects.Card
import com.kyledahlin.skryfall.test.card

interface SomeRepo {
    suspend fun getCard(): Card
}

class ExampleTest {
    fun `Repo should return the card`() {
        val mockRepo = object : SomeRepo {
            override suspend fun getCard(): Card {
                return card(name = "Krenko")
            }
        }
    }
}