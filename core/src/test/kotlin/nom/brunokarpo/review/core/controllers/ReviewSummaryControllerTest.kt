package nom.brunokarpo.review.core.controllers

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewListPaginatedUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class ReviewSummaryControllerTest {

    private companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val AVERAGE = BigDecimal.valueOf(Random.nextDouble(0.0, 5.0))!!
        val QTD_REVIEW = Random.nextInt()
    }

    @MockK
    private lateinit var retrieveSummaryReviewByRestaurantIdUseCase: RetrieveSummaryReviewByRestaurantIdUseCase

    @MockK
    private lateinit var retrieveSummaryReviewListPaginatedUseCase: RetrieveSummaryReviewListPaginatedUseCase

    @InjectMockKs
    private lateinit var sut: ReviewSummaryController

    @Test
    internal fun `should retrieve review summary`() {
        every {
            retrieveSummaryReviewByRestaurantIdUseCase.execute(RESTAURANT_ID)
        } returns ReviewSummary(restaurantId = RESTAURANT_ID, average = AVERAGE, qtdReview = QTD_REVIEW)

        val result: ReviewSummaryDTO = sut.retrieveByRestaurantId(RESTAURANT_ID)

        assertNotNull(result)
        assertEquals(RESTAURANT_ID, result.restaurantId)
        assertEquals(AVERAGE, result.average)
        assertEquals(QTD_REVIEW, result.qtdReview)
    }

    @Test
    internal fun `should retrieve summary review list paginated`() {
        every {
            retrieveSummaryReviewListPaginatedUseCase.execute(any(), any())
        } returns Pageable()

        sut.retrieveList(0, 0)

        verify(exactly = 1) {
            retrieveSummaryReviewListPaginatedUseCase.execute(any(), any())
        }

    }
}