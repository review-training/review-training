package nom.brunokarpo.review.micronaut.repositories

import nom.brunokarpo.review.core.model.Review
import nom.brunokarpo.review.core.repository.ReviewRepository
import java.sql.Connection
import java.sql.Statement
import java.sql.Types
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class ReviewRepositoryJdbcImpl(
        private val dataSource: DataSource
): ReviewRepository {

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