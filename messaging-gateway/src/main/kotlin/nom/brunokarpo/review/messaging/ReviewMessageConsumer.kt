package nom.brunokarpo.review.messaging

import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase
import nom.brunokarpo.review.messaging.messages.ReviewMessage

class ReviewMessageConsumer(
        private val createNewReviewUseCase: CreateNewReviewUseCase
) {

    fun process(message: ReviewMessage) {
        createNewReviewUseCase.execute(message.toModel())
    }

}