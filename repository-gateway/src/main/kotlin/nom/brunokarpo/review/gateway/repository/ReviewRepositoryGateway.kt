package nom.brunokarpo.review.gateway.repository

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.repository.ReviewRepository

interface ReviewRepositoryGateway: ReviewRepository {

    override fun create(review: Review)

}