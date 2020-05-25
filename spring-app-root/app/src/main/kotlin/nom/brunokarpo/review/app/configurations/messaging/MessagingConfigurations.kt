package nom.brunokarpo.review.app.configurations.messaging

import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfigurations {

    @Bean
    fun reviewMessageConsumer(createNewReviewUseCase: CreateNewReviewUseCase)
            = ReviewMessageConsumer(createNewReviewUseCase)

}