package nom.brunokarpo.review.messaging

import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.messaging.consumers.messages.ReviewMessage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class ReviewMessageConsumerTest {

    private companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val ORDER_ID = UUID.randomUUID()!!
        val USER_ID = UUID.randomUUID()!!
        val REVIEW = Random.nextInt(1, 5)
    }

    @MockK
    private lateinit var createNewReviewUseCaseMock: CreateNewReviewUseCase

    @InjectMockKs
    private lateinit var sut: ReviewMessageConsumer

    private lateinit var reviewMessage: ReviewMessage

    private lateinit var slot: CapturingSlot<Review>

    @BeforeEach
    internal fun setUp() {
        slot = slot()

        every {
            createNewReviewUseCaseMock.execute(capture(slot))
        } returns
                ReviewSummary(restaurantId = RESTAURANT_ID, average = BigDecimal.valueOf(Random.nextDouble()), qtdReview = Random.nextInt())

        reviewMessage = ReviewMessage(restaurantId = RESTAURANT_ID, orderId = ORDER_ID, userId = USER_ID, review = REVIEW)

        sut.process(message = reviewMessage)
    }

    @Test
    internal fun `should process review message`() {
        verify { createNewReviewUseCaseMock.execute(any()) }
    }

    @Test
    internal fun `should process message with correct restaurant id`() {
        assertEquals(RESTAURANT_ID, slot.captured.restaurantId)
    }

    @Test
    internal fun `should process message with correct user id`() {
        assertEquals(USER_ID, slot.captured.userId)
    }

    @Test
    internal fun `should process message with correct order id`() {
        assertEquals(ORDER_ID, slot.captured.orderId)
    }

    @Test
    internal fun `should process message with correct review value`() {
        assertEquals(REVIEW, slot.captured.review)
    }
}