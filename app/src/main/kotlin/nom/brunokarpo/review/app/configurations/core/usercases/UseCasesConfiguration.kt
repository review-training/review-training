package nom.brunokarpo.review.app.configurations.core.usercases

import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCasesConfiguration {

    @Bean
    fun createNewReviewUseCase(reviewRepository: ReviewRepository,
                               reviewSummaryRepository: ReviewSummaryRepository)
            = CreateNewReviewUserCase(reviewRepository, reviewSummaryRepository)

}