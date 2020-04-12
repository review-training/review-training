package nom.brunokarpo.review.core.controllers

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import java.util.*

class ReviewSummaryController(
        private val retrieveSummaryReviewByRestaurantIdUseCase: RetrieveSummaryReviewByRestaurantIdUseCase
) {

    fun retrieveByRestaurantId(restaurantId: UUID): ReviewSummaryDTO {
        return ReviewSummaryDTO(retrieveSummaryReviewByRestaurantIdUseCase.execute(restaurantId))
    }

}
