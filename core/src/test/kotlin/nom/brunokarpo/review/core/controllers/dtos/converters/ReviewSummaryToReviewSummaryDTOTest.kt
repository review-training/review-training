package nom.brunokarpo.review.core.controllers.dtos.converters

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.model.ReviewSummary
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

internal class ReviewSummaryToReviewSummaryDTOTest {

    companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val AVERAGE = BigDecimal.valueOf(Random.nextDouble())!!
        val QTD_REVIEW = Random.nextInt()
    }

    private lateinit var sut: ReviewSummaryToReviewSummaryDTO

    private lateinit var result: ReviewSummaryDTO

    @BeforeEach
    internal fun setUp() {
        sut = ReviewSummaryToReviewSummaryDTO()

        result = sut.convert( ReviewSummary(restaurantId = RESTAURANT_ID, average = AVERAGE, qtdReview = QTD_REVIEW) )
    }

    @Test
    internal fun `should convert review with restaurant id`() {
        assertEquals(RESTAURANT_ID, result.restaurantId)
    }

    @Test
    internal fun `should convert review with average`() {
        assertEquals(AVERAGE, result.average)
    }

    @Test
    internal fun `should convert review with qtd review`() {
        assertEquals(QTD_REVIEW, result.qtdReview)
    }
}