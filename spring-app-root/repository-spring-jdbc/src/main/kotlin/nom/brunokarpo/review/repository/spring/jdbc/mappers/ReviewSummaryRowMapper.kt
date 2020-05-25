package nom.brunokarpo.review.repository.spring.jdbc.mappers

import nom.brunokarpo.review.core.model.ReviewSummary
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.*

class ReviewSummaryRowMapper: RowMapper<ReviewSummary> {

    override fun mapRow(rs: ResultSet, rowNum: Int): ReviewSummary? {
        val restaurantId = rs.getObject("restaurant_id", UUID::class.java)
        val average = rs.getBigDecimal("average")
        val qtdReview = rs.getInt("qtd_review")

        return ReviewSummary(restaurantId = restaurantId, average = average, qtdReview = qtdReview)
    }
}