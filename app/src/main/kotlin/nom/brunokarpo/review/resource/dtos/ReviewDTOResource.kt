package nom.brunokarpo.review.resource.dtos

import nom.brunokarpo.review.core.controllers.dtos.ReviewDTO
import java.util.*

class ReviewDTOResource(
        var restaurantId: UUID,
        var userId: UUID,
        var orderId: UUID,
        var review: Int = 0
) {
    constructor(): this(restaurantId = UUID.randomUUID(), userId = UUID.randomUUID(), orderId = UUID.randomUUID())

    fun toReviewDTO()
            = ReviewDTO(restaurantId = this.restaurantId, userId = this.userId, orderId = this.orderId, review = this.review)
}