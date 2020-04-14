package nom.brunokarpo.review.repository.spring.jdbc

import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.gateway.repository.ReviewSummaryRepositoryGateway
import nom.brunokarpo.review.repository.spring.jdbc.mappers.ReviewSummaryRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.lang.Exception
import java.util.*
import kotlin.math.ceil

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

    override fun list(size: Int, page: Int): Pageable<ReviewSummary> {
        val countQuery = " select count(*) "
        val fromWhereQuery = " from review_summary "
        var sql = countQuery + fromWhereQuery

        val count = jdbcTemplate.queryForObject(countQuery + fromWhereQuery, mapOf<String, Any>(), Int::class.java)
        if (count == null || count == 0) {
            return Pageable(size = size, page = page)
        }

        val selectQuery = " select restaurant_id, average, qtd_review "
        val limitOffset = "order by average desc, qtd_review desc limit :limit offset :offset "
        sql = selectQuery + fromWhereQuery + limitOffset

        val params = mapOf("limit" to size,
                "offset" to page)

        val result = jdbcTemplate.query(sql, params, ReviewSummaryRowMapper())

        return Pageable(size = size, page =  page, count = count, content = result)
    }

}
