package nom.brunokarpo.review.repository.jdbc

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.gateway.repository.ReviewRepositoryGateway
import java.sql.Types
import javax.sql.DataSource

class ReviewRepositoryJdbc(private val dataSource: DataSource) : ReviewRepositoryGateway {

    override fun create(review: Review) {
        val sql = """
            INSERT INTO review (restaurant_id, user_id, order_id, created_at, review)
            VALUES (?, ?, ?, now(), ?)
        """.trimIndent()

        val ps = dataSource.connection.prepareStatement(sql)
        ps.setObject(1, review.restaurantId, Types.OTHER)
        ps.setObject(2, review.userId, Types.OTHER)
        ps.setObject(3, review.orderId, Types.OTHER)
        ps.setInt(4, review.review)

        ps.execute()
    }

}
