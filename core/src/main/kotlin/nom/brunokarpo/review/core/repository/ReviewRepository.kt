package nom.brunokarpo.review.core.repository

import nom.brunokarpo.review.core.model.Review

interface ReviewRepository {

    fun create(review: Review)

}
