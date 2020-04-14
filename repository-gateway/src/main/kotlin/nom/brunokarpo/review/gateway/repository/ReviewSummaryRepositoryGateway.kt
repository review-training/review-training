package nom.brunokarpo.review.gateway.repository

import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import java.util.*

interface ReviewSummaryRepositoryGateway: ReviewSummaryRepository {

    override fun getByRestaurantId(restaurantId: UUID): ReviewSummary?

    override fun list(size: Int, page: Int): Pageable<ReviewSummary>
}