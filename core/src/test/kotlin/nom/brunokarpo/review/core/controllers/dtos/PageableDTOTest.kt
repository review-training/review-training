package nom.brunokarpo.review.core.controllers.dtos

import io.mockk.every
import io.mockk.mockk
import nom.brunokarpo.review.core.controllers.dtos.converters.ModelDTOConverter
import nom.brunokarpo.review.core.controllers.dtos.converters.ReviewSummaryToReviewSummaryDTO
import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals

internal class PageableDTOTest {

    private companion object {
        const val SIZE = 5
        const val PAGE = 3
        const val FIRST = false
        const val LAST = false
        const val TOTAL_PAGES = 5
    }

    private lateinit var mockConverter: ModelDTOConverter<List<Any>, Any>

    @BeforeEach
    internal fun setUp() {
        mockConverter = mockk()
        every { mockConverter.convert(any()) } returns emptyList()
    }

    @Test
    internal fun `should convert to DTO with size`() {
        val pageable = createPageableDTO(emptyList())

        val result = PageableDTO(pageable) {mockConverter.convert(it)}

        assertEquals(SIZE, result.size)
    }

    @Test
    internal fun `should convert to DTO with page`() {
        val pageable = createPageableDTO(emptyList())

        val result = PageableDTO(pageable) {mockConverter.convert(it)}

        assertEquals(PAGE, result.page)
    }

    @Test
    internal fun `should convert to DTO with first parameter`() {
        val pageable = createPageableDTO(emptyList())

        val result = PageableDTO(pageable) {mockConverter.convert(it)}

        assertEquals(FIRST, result.first)
    }

    @Test
    internal fun `should convert to DTO with last parameter`() {
        val pageable = createPageableDTO(emptyList())

        val result = PageableDTO(pageable) {mockConverter.convert(it)}

        assertEquals(LAST, result.last)
    }

    @Test
    internal fun `should convert to DTO with number of elements`() {
        val pageable = createPageableDTO(listOf(Any(), Any(), Any()))

        val result = PageableDTO(pageable) {mockConverter.convert(it)}

        assertEquals(3, result.numberOfElements)
    }

    @Test
    internal fun `should convert to DTO with total of pages`() {
        val pageable = createPageableDTO(emptyList())

        val result = PageableDTO(pageable) {mockConverter.convert(it)}

        assertEquals(TOTAL_PAGES, result.totalPages)
    }

    @Test
    internal fun `should convert to DTO with content type in DTO`() {
        val restaurantId = UUID.randomUUID()
        val qtdReview = Random.nextInt()
        val average = BigDecimal.valueOf(Random.nextDouble())
        val reviewSummary = ReviewSummary(restaurantId = restaurantId, qtdReview = qtdReview, average = average)

        val pageable = createReviewSummaryPageable(listOf(reviewSummary))

        val result = PageableDTO(pageable) {
            it.map { element -> ReviewSummaryToReviewSummaryDTO().convert(element as ReviewSummary) }
        }

        result.content[0].apply {
            assertEquals(restaurantId, this.restaurantId)
            assertEquals(qtdReview, this.qtdReview)
            assertEquals(average, this.average)
        }
    }

    private fun createReviewSummaryPageable(reviewSummarys: List<ReviewSummary>): Pageable<ReviewSummary> {
        return Pageable(size = SIZE, page = PAGE,
                first = FIRST, last = LAST,
                totalPages = TOTAL_PAGES,
                numberOfElements = reviewSummarys.size, content = reviewSummarys)
    }

    private fun createPageableDTO(models: List<Any>): Pageable<Any> {
        return Pageable(size = SIZE, page = PAGE,
                first = FIRST, last = LAST,
                totalPages = TOTAL_PAGES,
                numberOfElements = models.size, content = models)
    }
}