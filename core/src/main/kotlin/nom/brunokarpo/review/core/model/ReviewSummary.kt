package nom.brunokarpo.review.core.model

import java.math.BigDecimal
import java.util.*

data class ReviewSummary(
        val restaurantId: UUID,
        var qtdReview: Int,
        var average: BigDecimal
)