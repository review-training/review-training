package nom.brunokarpo.review.repository.jdbc

import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.gateway.repository.ReviewSummaryRepositoryGateway
import java.sql.Types
import java.util.*
import javax.sql.DataSource

class ReviewSummaryRepositoryJdbc(
        private val dataSource: DataSource
): ReviewSummaryRepositoryGateway {

    override fun getByRestaurantId(restaurantId: UUID): ReviewSummary? {
        val sql = "SELECT restaurant_id, average, qtd_review FROM review_summary WHERE restaurant_id = ?"
        val ps = dataSource.connection.prepareStatement(sql)
        ps.setObject(1, restaurantId, Types.OTHER)

        val rs = ps.executeQuery()
        if (rs.next()) {
            val restId = rs.getObject("restaurant_id", UUID::class.java)
            val average = rs.getBigDecimal("average")
            val qtdReview = rs.getInt("qtd_review")
            return ReviewSummary(restaurantId = restId, average = average, qtdReview = qtdReview)
        }
        return null
    }

}
