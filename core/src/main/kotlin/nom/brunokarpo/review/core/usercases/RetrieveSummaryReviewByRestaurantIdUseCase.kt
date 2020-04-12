package nom.brunokarpo.review.core.usercases

import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import java.util.*

class RetrieveSummaryReviewByRestaurantIdUseCase(
        private val reviewSummaryRepository: ReviewSummaryRepository
) {

    fun execute(restaurantId: UUID): ReviewSummary {
        return reviewSummaryRepository.getByRestaurantId(restaurantId).get()
    }

}
