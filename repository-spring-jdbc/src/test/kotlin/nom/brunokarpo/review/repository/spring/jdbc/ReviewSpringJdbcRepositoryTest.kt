package nom.brunokarpo.review.repository.spring.jdbc

import nom.brunokarpo.review.core.model.Review
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ReviewSpringJdbcRepositoryTest: JdbcDatabaseTest() {

    @Autowired
    private lateinit var sut: ReviewSpringJdbcRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    internal fun `should create new review`() {
        val restaurantId = UUID.randomUUID()
        val userId = UUID.randomUUID()
        val orderId = UUID.randomUUID()

        val review = Review(restaurantId = restaurantId, userId = userId, orderId = orderId, review = 5)

        sut.create(review)

        val result = jdbcTemplate.query("select restaurant_id, user_id, order_id, review from review " +
                " where restaurant_id = ? and user_id = ? and order_id = ? and review = ?"
                , arrayOf(restaurantId, userId, orderId, 5)) {rs, _ ->
            Review(restaurantId = rs.getObject("restaurant_id", UUID::class.java),
                    orderId = rs.getObject("order_id", UUID::class.java),
                    userId = rs.getObject("user_id", UUID::class.java),
                    review = rs.getInt("review"))
        }[0]

        assertNotNull(result)
        assertEquals(restaurantId, result.restaurantId)
        assertEquals(userId, result.userId)
        assertEquals(orderId, result.orderId)
        assertEquals(5, result.review)
    }
}