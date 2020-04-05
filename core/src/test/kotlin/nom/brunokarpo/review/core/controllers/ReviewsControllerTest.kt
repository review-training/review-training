package nom.brunokarpo.review.core.controllers

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import nom.brunokarpo.review.core.controllers.dtos.ReviewDTO
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class ReviewsControllerTest {

    private companion object {
        val REST_ID = UUID.randomUUID()!!
        val USER_ID = UUID.randomUUID()!!
        val ORDER_ID = UUID.randomUUID()!!
        const val REVIEW = 5
        const val QTD_REVIEW = 5157
        val AVERAGE = BigDecimal.valueOf(4.7)!!
    }

    @MockK
    private lateinit var createNewReviewUserCaseMock: CreateNewReviewUserCase

    @InjectMockKs
    private lateinit var sut: ReviewsController

    private lateinit var reviewDto: ReviewDTO

    @BeforeEach
    internal fun setUp() {
        reviewDto = ReviewDTO(restaurantId = REST_ID, userId = USER_ID, orderId = ORDER_ID, review = REVIEW)
        every {
            createNewReviewUserCaseMock.execute(any())
        } returns ReviewSummary(restaurantId = REST_ID, qtdReview = QTD_REVIEW, average = AVERAGE)
    }

    @Test
    internal fun `should receive new review and send to property user case`() {
        sut.createNew(reviewDto)

        verify { createNewReviewUserCaseMock.execute(any()) }
    }

    @Test
    internal fun `should create new review with correct parameters`() {
        val slot = slot<Review>()

        every {
            createNewReviewUserCaseMock.execute(capture(slot))
        } returns ReviewSummary(restaurantId = REST_ID, qtdReview = QTD_REVIEW, average = AVERAGE)

        sut.createNew(reviewDto)

        val review = slot.captured
        assertEquals(REST_ID, review.restaurantId)
        assertEquals(USER_ID, review.userId)
        assertEquals(ORDER_ID, review.orderId)
        assertEquals(REVIEW, review.review)
    }

    @Test
    internal fun `should return summary dto as response`() {
        val result: ReviewSummaryDTO = sut.createNew(reviewDto)

        assertEquals(REST_ID, result.restaurantId)
        assertEquals(QTD_REVIEW, result.qtdReview)
        assertEquals(AVERAGE, result.average)
    }
}