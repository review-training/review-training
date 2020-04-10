package nom.brunokarpo.review.repository.spring.jdbc

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.model.ReviewSummary
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.math.BigDecimal
import java.util.*

@SqlGroup(
        Sql(value = ["/load-database.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        Sql(value = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class ReviewSpringJdbcRepositoryTest: DatabaseTestBase() {

    @Autowired
    private lateinit var sut: ReviewSpringJdbcRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun `should create new review`() {
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

        assertThat(result).isNotNull
        assertThat(result.restaurantId).isEqualTo(restaurantId)
        assertThat(result.userId).isEqualTo(userId)
        assertThat(result.orderId).isEqualTo(orderId)
        assertThat(result.review).isEqualTo(5)
    }

    @Test
    fun `should recalculate review summary by adding new review`() {
        val restaurantId = UUID.fromString("5c5749a5-e0e3-42a0-9d26-a428eb2e5344")
        val userId = UUID.randomUUID()
        val orderId = UUID.randomUUID()

        val review = Review(restaurantId = restaurantId, userId = userId, orderId = orderId, review = 5)

        sut.create(review)

        val result = jdbcTemplate.query("select * from review_summary where restaurant_id = ?",
                arrayOf(restaurantId)) {rs, _ ->
            ReviewSummary(restaurantId = rs.getObject("restaurant_id", UUID::class.java),
                    qtdReview = rs.getInt("qtd_review"), average = rs.getBigDecimal("average"))
        }[0]

        assertThat(result).isNotNull
        assertThat(result.restaurantId).isEqualTo(restaurantId)
        assertThat(result.average).isEqualTo(BigDecimal.valueOf(4.6))
        assertThat(result.qtdReview).isEqualTo(5)
    }
}