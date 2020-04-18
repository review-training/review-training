package nom.brunokarpo.review.messaging.messages

import nom.brunokarpo.review.core.model.Review
import java.util.*

data class ReviewMessage(
        val restaurantId: UUID,
        val userId: UUID,
        val orderId: UUID,
        var review: Int
) {
    fun toModel() = Review(restaurantId = this.restaurantId, orderId = this.orderId, userId = this.userId, review = this.review)
}