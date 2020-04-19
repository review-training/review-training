package nom.brunokarpo.review.app.configurations.messaging

import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import nom.brunokarpo.review.messaging.ReviewMessageConsumer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfigurations {

    @Bean
    fun reviewMessageConsumer(createNewReviewUserCase: CreateNewReviewUserCase)
            = ReviewMessageConsumer(createNewReviewUserCase)

}