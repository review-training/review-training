package nom.brunokarpo.review.quarkus.configurations.core.controllers

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.controllers.ReviewSummaryController
import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewListPaginatedUseCase
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class ControllersConfiguration {

    @Produces
    @DefaultBean
    fun reviewController(createNewReviewUseCase: CreateNewReviewUseCase)
            = ReviewsController(createNewReviewUseCase)

    @Produces
    @DefaultBean
    fun reviewSummaryController(retrieveSummaryReviewByRestaurantIdUseCase: RetrieveSummaryReviewByRestaurantIdUseCase,
                                retrieveSummaryReviewListPaginatedUseCase: RetrieveSummaryReviewListPaginatedUseCase)
            = ReviewSummaryController(retrieveSummaryReviewByRestaurantIdUseCase, retrieveSummaryReviewListPaginatedUseCase)

}