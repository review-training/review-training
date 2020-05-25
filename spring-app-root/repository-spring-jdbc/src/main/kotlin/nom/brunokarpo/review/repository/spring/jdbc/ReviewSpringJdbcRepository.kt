package nom.brunokarpo.review.repository.spring.jdbc

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.gateway.repository.ReviewRepositoryGateway
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ReviewSpringJdbcRepository(
        private val jdbcTemplate: NamedParameterJdbcTemplate
    ): ReviewRepositoryGateway {

    override fun create(review: Review) {
        val sql = """
            INSERT INTO review(restaurant_id, user_id, order_id, created_at, review)
            VALUES (:restaurantId, :userId, :orderId, now(), :review)
        """.trimIndent()
        val params = mapOf("restaurantId" to review.restaurantId,
                "userId" to review.userId, "orderId" to review.orderId, "review" to review.review)

        jdbcTemplate.update(sql, params)
    }

}
