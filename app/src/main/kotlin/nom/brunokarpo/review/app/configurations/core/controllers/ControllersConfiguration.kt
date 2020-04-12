package nom.brunokarpo.review.app.configurations.core.controllers

import nom.brunokarpo.review.core.controllers.ReviewSummaryController
import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ControllersConfiguration {

    @Bean
    fun reviewController(createNewReviewUserCase: CreateNewReviewUserCase)
            = ReviewsController(createNewReviewUserCase)

    @Bean
    fun reviewSummaryController(retrieveSummaryReviewByRestaurantIdUseCase: RetrieveSummaryReviewByRestaurantIdUseCase)
            = ReviewSummaryController(retrieveSummaryReviewByRestaurantIdUseCase)
}