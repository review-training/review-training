package nom.brunokarpo.review.repository.spring.jdbc

import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.gateway.repository.ReviewSummaryRepositoryGateway
import nom.brunokarpo.review.repository.spring.jdbc.mappers.ReviewSummaryRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.lang.Exception
import java.util.*

@Repository
class ReviewSummarySpringJdbcRepository(
        private val jdbcTemplate: NamedParameterJdbcTemplate
): ReviewSummaryRepositoryGateway {


    override fun getByRestaurantId(restaurantId: UUID): ReviewSummary? {
        val sql = """
            SELECT restaurant_id, average, qtd_review FROM review_summary WHERE restaurant_id = :restaurantId
        """.trimIndent()
        val params = mapOf("restaurantId" to restaurantId)
        return try {
            jdbcTemplate.queryForObject(sql, params, ReviewSummaryRowMapper())
        } catch (ex: Exception) {
            null
        }
    }

}
