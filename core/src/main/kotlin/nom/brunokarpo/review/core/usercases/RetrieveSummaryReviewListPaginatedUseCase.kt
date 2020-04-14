package nom.brunokarpo.review.core.usercases

import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository

class RetrieveSummaryReviewListPaginatedUseCase(
        private val reviewSummaryRepository: ReviewSummaryRepository
) {

    fun execute(size: Int, page: Int): Pageable<ReviewSummary> {
        return reviewSummaryRepository.list(size, page)
    }
}
