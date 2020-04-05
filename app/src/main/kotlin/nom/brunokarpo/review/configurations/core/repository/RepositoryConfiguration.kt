package nom.brunokarpo.review.configurations.core.repository

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import java.math.BigDecimal
import java.util.*
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class RepositoryConfiguration {

    @Produces
    @DefaultBean
    fun reviewRepository() = object : ReviewRepository {
        override fun create(review: Review) {
            println("I created it. Believe it!")
        }
    }

    @Produces
    @DefaultBean
    fun reviewSummaryRepository() = object : ReviewSummaryRepository {
        override fun getByRestaurantId(restaurantId: UUID): ReviewSummary {
            return ReviewSummary(restaurantId, 1, BigDecimal.valueOf(5.0))
        }
    }

}