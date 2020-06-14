package nom.brunokarpo.review.micronaut.configurations.core.controllers

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import nom.brunokarpo.review.core.controllers.ReviewSummaryController
import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewListPaginatedUseCase

@Factory
class ControllersFactory {

    @Bean
    fun reviewsController(createNewReviewUseCase: CreateNewReviewUseCase)
            = ReviewsController(createNewReviewUseCase)

    @Bean
    fun reviewSummaryControllers(
            retrieveSummaryReviewByRestaurantIdUseCase: RetrieveSummaryReviewByRestaurantIdUseCase,
            retrieveSummaryReviewListPaginatedUseCase: RetrieveSummaryReviewListPaginatedUseCase)
            = ReviewSummaryController(retrieveSummaryReviewByRestaurantIdUseCase, retrieveSummaryReviewListPaginatedUseCase)
}