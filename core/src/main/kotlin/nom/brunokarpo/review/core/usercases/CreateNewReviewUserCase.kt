package nom.brunokarpo.review.core.usercases

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository

class CreateNewReviewUserCase(
        private val reviewRepository: ReviewRepository,
        private val reviewSummaryRepository: ReviewSummaryRepository
) {

    fun execute(review: Review): ReviewSummary {
        reviewRepository.create(review)
        return reviewSummaryRepository.getByRestaurantId(review.restaurantId).get()
    }

}
