package nom.brunokarpo.review.core.controllers.dtos

import nom.brunokarpo.review.core.model.ReviewSummary
import java.math.BigDecimal
import java.util.*

data class ReviewSummaryDTO(
        val restaurantId: UUID,
        val qtdReview: Int,
        val average: BigDecimal
) {
    constructor(reviewSummary: ReviewSummary)
            : this(restaurantId = reviewSummary.restaurantId,
                    qtdReview = reviewSummary.qtdReview,
                    average = reviewSummary.average)
}
