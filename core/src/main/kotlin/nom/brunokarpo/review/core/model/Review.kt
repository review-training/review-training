package nom.brunokarpo.review.core.model

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class Review(
        val restaurantId: UUID,
        val userId: UUID,
        val orderId: UUID,
        val date: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),
        var review: Int
)