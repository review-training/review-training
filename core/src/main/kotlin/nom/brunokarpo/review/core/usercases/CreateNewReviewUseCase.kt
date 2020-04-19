package nom.brunokarpo.review.core.usercases

import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository

class CreateNewReviewUseCase(
        private val reviewRepository: ReviewRepository,
        private val reviewSummaryRepository: ReviewSummaryRepository,
        private val reviewSummaryPublisher: ReviewSummaryPublisher
) {

    fun execute(review: Review): ReviewSummary {
        reviewRepository.create(review)
        val summary = reviewSummaryRepository.getByRestaurantId(review.restaurantId)!!
        reviewSummaryPublisher.publish(summary)
        return summary
    }

}
