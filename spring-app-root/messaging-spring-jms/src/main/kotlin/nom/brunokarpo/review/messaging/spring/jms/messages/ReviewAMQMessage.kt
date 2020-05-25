package nom.brunokarpo.review.messaging.spring.jms.messages

import nom.brunokarpo.review.messaging.consumers.messages.ReviewMessage
import java.util.*

data class ReviewAMQMessage(
        var restaurantId: UUID? = null,
        var orderId: UUID? = null,
        var userId: UUID? = null,
        var review: Int? = null
) {

    fun asMessage() = ReviewMessage(restaurantId = this.restaurantId!!, orderId = this.orderId!!, userId = this.userId!!, review = this.review!!)
}