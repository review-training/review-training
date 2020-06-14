package nom.brunokarpo.review.quarkus.configurations.core.usecases

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewListPaginatedUseCase
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class UseCasesConfiguration {

    @Produces
    @DefaultBean
    fun createNewReviewUseCase(reviewRepository: ReviewRepository,
                               reviewSummaryRepository: ReviewSummaryRepository,
                               reviewSummaryPublisher: ReviewSummaryPublisher)
            = CreateNewReviewUseCase(reviewRepository, reviewSummaryRepository, reviewSummaryPublisher)

    @Produces
    @DefaultBean
    fun retrieveSummaryReviewByRestaurantIdUseCase(reviewSummaryRepository: ReviewSummaryRepository)
            = RetrieveSummaryReviewByRestaurantIdUseCase(reviewSummaryRepository)

    @Produces
    @DefaultBean
    fun retrieveSummaryReviewListPaginatedUseCase(reviewSummaryRepository: ReviewSummaryRepository)
            = RetrieveSummaryReviewListPaginatedUseCase(reviewSummaryRepository)
}