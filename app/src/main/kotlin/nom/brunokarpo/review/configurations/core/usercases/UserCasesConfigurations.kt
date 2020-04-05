package nom.brunokarpo.review.configurations.core.usercases

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class UserCasesConfigurations {

    @Produces
    @DefaultBean
    fun createNewReviewUserCase(reviewRepository: ReviewRepository,
                                reviewSummaryRepository: ReviewSummaryRepository)
            = CreateNewReviewUserCase(reviewRepository, reviewSummaryRepository)

}