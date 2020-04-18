package nom.brunokarpo.review.messaging

import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import nom.brunokarpo.review.messaging.messages.ReviewMessage

class ReviewMessageConsumer(
        private val createNewReviewUserCase: CreateNewReviewUserCase
) {

    fun process(message: ReviewMessage) {
        createNewReviewUserCase.execute(message.toModel())
    }

}