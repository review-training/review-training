package nom.brunokarpo.review.resource.dtos

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import java.math.BigDecimal
import java.util.*

class ReviewSummaryDTOResource(
        var restaurantId: UUID,
        var qtdReview: Int,
        var average: BigDecimal
) {
    constructor()
            : this(restaurantId = UUID.randomUUID(), qtdReview = 0, average = BigDecimal.valueOf(5.0))

    constructor(reviewSummaryDTO: ReviewSummaryDTO)
            : this(restaurantId = reviewSummaryDTO.restaurantId,
                    qtdReview = reviewSummaryDTO.qtdReview,
                    average = reviewSummaryDTO.average)
}