package nom.brunokarpo.review.micronaut.configurations.core.messaging

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer

@Factory
class MessagingFactory {

    @Bean
    fun reviewMessageConsumer(createNewReviewUseCase: CreateNewReviewUseCase)
            = ReviewMessageConsumer(createNewReviewUseCase)
}