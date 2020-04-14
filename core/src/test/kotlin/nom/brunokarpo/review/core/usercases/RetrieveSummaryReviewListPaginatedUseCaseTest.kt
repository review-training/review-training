package nom.brunokarpo.review.core.usercases

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class RetrieveSummaryReviewListPaginatedUseCaseTest {

    @MockK
    private lateinit var reviewSummaryRepository: ReviewSummaryRepository

    @InjectMockKs
    private lateinit var sut: RetrieveSummaryReviewListPaginatedUseCase

    @Test
    internal fun `should retrieve review summary list paginated`() {
        val review1 = ReviewSummary(restaurantId = UUID.randomUUID(), qtdReview = Random.nextInt(), average = BigDecimal.valueOf(Random.nextDouble()))
        val review2 = ReviewSummary(restaurantId = UUID.randomUUID(), qtdReview = Random.nextInt(), average = BigDecimal.valueOf(Random.nextDouble()))
        val review3 = ReviewSummary(restaurantId = UUID.randomUUID(), qtdReview = Random.nextInt(), average = BigDecimal.valueOf(Random.nextDouble()))
        val review4 = ReviewSummary(restaurantId = UUID.randomUUID(), qtdReview = Random.nextInt(), average = BigDecimal.valueOf(Random.nextDouble()))
        val review5 = ReviewSummary(restaurantId = UUID.randomUUID(), qtdReview = Random.nextInt(), average = BigDecimal.valueOf(Random.nextDouble()))
        every {
            reviewSummaryRepository.list(size = 5, page = 0)
        } returns Pageable(size = 5, totalPages = 1, first = true, last = true, numberOfElements = 5, content = listOf(review1, review2, review3, review4, review5))

        val result: Pageable<ReviewSummary> = sut.execute(size = 5, page = 0)

        assertNotNull(result)
        assertEquals(5, result.size)
        assertEquals(0, result.page)
        assertEquals(1, result.totalPages)
        assertTrue { result.first }
        assertTrue { result.last }
        assertEquals(5, result.numberOfElements)
        assertTrue {
            result.content.containsAll(listOf(review1, review2, review3, review4, review5))
        }
    }
}