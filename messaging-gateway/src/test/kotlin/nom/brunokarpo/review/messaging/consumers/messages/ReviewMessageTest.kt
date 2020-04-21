package nom.brunokarpo.review.messaging.consumers.messages

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals

internal class ReviewMessageTest {

    companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val ORDER_ID = UUID.randomUUID()!!
        val USER_ID = UUID.randomUUID()!!
        val REVIEW = Random.nextInt(1, 5)
    }

    private lateinit var sut: ReviewMessage

    @BeforeEach
    internal fun setUp() {
        sut = ReviewMessage(restaurantId =  RESTAURANT_ID, orderId = ORDER_ID, userId = USER_ID, review = REVIEW)
    }

    @Test
    internal fun `should create review model with restaurant id`() {
        val result = sut.toModel()

        assertEquals(RESTAURANT_ID, result.restaurantId)
    }

    @Test
    internal fun `should create review model with user id`() {
        val result = sut.toModel()

        assertEquals(USER_ID, result.userId)
    }

    @Test
    internal fun `should create review model with order id`() {
        val result = sut.toModel()

        assertEquals(ORDER_ID, result.orderId)
    }

    @Test
    internal fun `should create review model with review`() {
        val result = sut.toModel()

        assertEquals(REVIEW, result.review)
    }
}