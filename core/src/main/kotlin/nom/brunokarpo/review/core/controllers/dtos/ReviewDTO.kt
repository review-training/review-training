package nom.brunokarpo.review.core.controllers.dtos

import nom.brunokarpo.review.core.model.Review
import java.util.*

data class ReviewDTO(
        val restaurantId: UUID,
        val userId: UUID,
        val orderId: UUID,
        var review: Int
) {
    fun toModel() = Review(restaurantId = this.restaurantId, userId = this.userId, orderId = this.orderId, review = this.review)
}
