package nom.brunokarpo.review.app.resources.dtos

import nom.brunokarpo.review.core.controllers.dtos.ReviewDTO
import java.util.*

data class ReviewDTOResource(
        var restaurantId: UUID? = null,
        var userId: UUID? = null,
        var orderId: UUID? = null,
        var review: Int? = null
) {
    fun toModel() = ReviewDTO(restaurantId = restaurantId!!, userId = userId!!, orderId = orderId!!, review = review!! )
}
