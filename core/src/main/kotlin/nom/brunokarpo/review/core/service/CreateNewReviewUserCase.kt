package nom.brunokarpo.review.core.service

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import java.math.BigDecimal
import java.util.*

class CreateNewReviewUserCase(
        private val reviewRepository: ReviewRepository,
        private val reviewSummaryRepository: ReviewSummaryRepository
) {

    fun execute(review: Review): ReviewSummary {
        reviewRepository.create(review)
        return reviewSummaryRepository.getByRestaurantId(review.restaurantId)
    }

}
