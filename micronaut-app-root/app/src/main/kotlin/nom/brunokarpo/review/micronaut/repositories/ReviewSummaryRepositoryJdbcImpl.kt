package nom.brunokarpo.review.micronaut.repositories

import nom.brunokarpo.review.core.model.Pageable
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Types
import java.util.*
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class ReviewSummaryRepositoryJdbcImpl(
    private val dataSource: DataSource
): ReviewSummaryRepository {

    override fun getByRestaurantId(restaurantId: UUID): ReviewSummary? {
        val sql = """
            SELECT restaurant_id, average, qtd_review FROM review_summary WHERE restaurant_id = ?
        """.trimIndent()

        var connection: Connection? = null
        var statement: Statement? = null
        var rs: ResultSet? = null
        try {
            connection = dataSource.connection
            statement = connection.prepareStatement(sql)
            statement.setObject(1, restaurantId, Types.OTHER)
            rs = statement.executeQuery()
            if (rs.next()) {
                val restId = rs.getObject("restaurant_id", UUID::class.java)
                val average = rs.getBigDecimal("average")
                val qtdReview = rs.getInt("qtd_review")
                return ReviewSummary(restaurantId = restId, qtdReview = qtdReview, average = average)
            }
        } finally {
            rs?.close()
            statement?.close()
            connection?.close()
        }
        return null
    }

    override fun list(size: Int, page: Int): Pageable<ReviewSummary> {
        var connection: Connection? = null
        var statement: Statement? = null
        var rs: ResultSet? = null

        try {
            val countQuery = " select count(*) as total "
            val fromWhereQuery = " from review_summary "
            var sql = countQuery + fromWhereQuery
            var totalElements: Int? = null

            connection = dataSource.connection
            statement = connection.prepareStatement(sql)
            rs = statement.executeQuery()
            if (rs.next()) {
                totalElements = rs.getInt("total")
            }

            if (totalElements == null || totalElements == 0) {
                return Pageable(size = size, page = page)
            }

            val selectQuery = " select restaurant_id, average, qtd_review "
            val limitOffset = " order by average desc, qtd_review desc limit ? offset ?"
            sql = selectQuery + fromWhereQuery + limitOffset

            statement = connection.prepareStatement(sql)
            statement.setInt(1, size)
            statement.setInt(2, page)

            val result = mutableListOf<ReviewSummary>()
            rs = statement.executeQuery()

            while(rs.next()) {
                result += ReviewSummary(
                        restaurantId = rs.getObject("restaurant_id", UUID::class.java),
                        qtdReview = rs.getInt("qtd_review"),
                        average = rs.getBigDecimal("average")
                )
            }

            return Pageable(size = size, page = page, count = totalElements, content = result)

        } finally {
            rs?.close()
            statement?.close()
            connection?.close()
        }
    }
}