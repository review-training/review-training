package nom.brunokarpo.review.quarkus.configurations.core.messaging

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class MessagingConfiguration {

    @Produces
    @DefaultBean
    fun reviewMessageConsumer(createNewReviewUseCase: CreateNewReviewUseCase)
            = ReviewMessageConsumer(createNewReviewUseCase)
}