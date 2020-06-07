package nom.brunokarpo.review.quarkus.repositories

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.gateway.repository.ReviewRepositoryGateway
import java.sql.Connection
import java.sql.Statement
import java.sql.Types
import javax.sql.DataSource

class ReviewRepositoryJdbc(
        private val dataSource: DataSource
): ReviewRepositoryGateway{

    override fun create(review: Review) {
        var connection: Connection? = null
        var statement: Statement? = null
        try {
            val sql = """
                INSERT INTO review(restaurant_id, user_id, order_id, created_at, review)
                VALUES (?, ?, ?, now(), ?)
            """.trimIndent()
            connection = dataSource.connection
            statement = connection.prepareStatement(sql)
            statement.setObject(1, review.restaurantId, Types.OTHER)
            statement.setObject(2, review.userId, Types.OTHER)
            statement.setObject(3, review.orderId, Types.OTHER)
            statement.setInt(4, review.review)
            statement.executeUpdate()
        } finally {
            statement?.close()
            connection?.close()
        }
    }
}