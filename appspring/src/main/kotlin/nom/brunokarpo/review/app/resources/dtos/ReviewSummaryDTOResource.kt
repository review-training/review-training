package nom.brunokarpo.review.app.resources.dtos

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import java.math.BigDecimal
import java.util.*

data class ReviewSummaryDTOResource(
        var restaurantId: UUID? = null,
        var average: BigDecimal? = null,
        var qtdReview: Int? = null
) {
    constructor(reviewSummaryDTO: ReviewSummaryDTO)
            : this(restaurantId = reviewSummaryDTO.restaurantId, average = reviewSummaryDTO.average,
                    qtdReview = reviewSummaryDTO.qtdReview)

}
