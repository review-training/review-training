package nom.brunokarpo.review.core.usercases

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class RetrieveSummaryReviewByRestaurantIdUseCaseTest {

    @MockK
    private lateinit var reviewSummaryRepository: ReviewSummaryRepository

    @InjectMockKs
    private lateinit var sut: RetrieveSummaryReviewByRestaurantIdUseCase

    @Test
    internal fun `should retrieve review summary by restaurant id`() {
        val restaurantId = UUID.randomUUID()!!
        val reviewSummary
                = ReviewSummary(restaurantId = restaurantId,
                                qtdReview = Random.nextInt(),
                                average = BigDecimal.valueOf(Random.nextDouble()))

        every {
            reviewSummaryRepository.getByRestaurantId(restaurantId)
        } returns
                Optional.of(reviewSummary)

        val result = sut.execute(restaurantId)

        assertEquals(reviewSummary, result)
    }
}