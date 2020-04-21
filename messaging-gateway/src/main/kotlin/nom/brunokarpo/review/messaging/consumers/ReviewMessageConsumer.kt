package nom.brunokarpo.review.messaging.consumers

import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.messaging.consumers.messages.ReviewMessage

class ReviewMessageConsumer(
        private val createNewReviewUseCase: CreateNewReviewUseCase
) {

    fun process(message: ReviewMessage) {
        createNewReviewUseCase.execute(message.toModel())
    }

}