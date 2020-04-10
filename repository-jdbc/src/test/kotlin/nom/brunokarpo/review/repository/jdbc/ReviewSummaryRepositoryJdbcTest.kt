package nom.brunokarpo.review.repository.jdbc

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ReviewSummaryRepositoryJdbcTest: DatabaseTestBase() {

    private lateinit var sut: ReviewSummaryRepositoryJdbc

    @BeforeEach
    internal fun setUp() {
        sut = ReviewSummaryRepositoryJdbc(DATA_SOURCE)

        loadDatabase()
    }

    @Test
    internal fun `should load review summary`() {
        val restaurantId = UUID.fromString("811bfc9f-4ac6-496c-ac1a-566de4974bd0")

        val result = sut.getByRestaurantId(restaurantId)

        assertNotNull(result)
        assertEquals(restaurantId, result.restaurantId)
        assertEquals(BigDecimal.valueOf(4.9), result.average)
        assertEquals(520, result.qtdReview)
    }

    @Test
    internal fun `should not load review summary if restaurant id does not exists`() {
        val restaurantId = UUID.fromString("a3631bb7-39b5-4e96-a07b-1f79a2aa6749")

        val result = sut.getByRestaurantId(restaurantId)

        assertNull(result)
    }

    @AfterEach
    internal fun tearDown() {
        cleanDatabase()
    }
}