package nom.brunokarpo.review.core.usercases

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verifyAll
import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class CreateNewReviewUserCaseTest {

    private companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val USER_ID = UUID.randomUUID()!!
        val ORDER_ID = UUID.randomUUID()!!
        const val REVIEW = 4
        const val REVIEW_QTD = 5
        val AVERAGE = BigDecimal.valueOf(3.3)!!
    }

    @MockK
    private lateinit var reviewSummaryPublisher: ReviewSummaryPublisher

    @MockK
    private lateinit var reviewRepository: ReviewRepository

    @MockK
    private lateinit var reviewSummaryRepository: ReviewSummaryRepository

    @InjectMockKs
    private lateinit var sut: CreateNewReviewUserCase

    private lateinit var review: Review
    private lateinit var reviewSummary: ReviewSummary

    @BeforeEach
    internal fun setUp() {
        review = Review(restaurantId = RESTAURANT_ID, userId = USER_ID, orderId = ORDER_ID, review = REVIEW)
        reviewSummary = ReviewSummary(restaurantId = RESTAURANT_ID, qtdReview = REVIEW_QTD, average = AVERAGE)
        every { reviewRepository.create(any()) } just Runs
        every { reviewSummaryPublisher.publish(any()) } just Runs
        every { reviewSummaryRepository.getByRestaurantId(RESTAURANT_ID) } returns reviewSummary

    }

    @Test
    internal fun `should create new received review`() {
        sut.execute(review)

        verifyAll { reviewRepository.create(review) }
    }

    @Test
    internal fun `should return review summary when create new review`() {
        val result = sut.execute(review)

        assertEquals(reviewSummary, result)
    }

    @Test
    internal fun `should publish review summary after create a review`() {
        val result = sut.execute(review)

        verifyAll { reviewSummaryPublisher.publish(result) }
    }
}