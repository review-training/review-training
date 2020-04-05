package nom.brunokarpo.review.repository.jdbc

import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainerProvider
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class ReviewRepositoryJdbcTest {

    @Container
    val postgresContainer = PostgreSQLContainerProvider()
            .newInstance()
            .withDatabaseName("review")
            .withUsername("review-app")
            .withPassword("review-app")


    @Test
    internal fun `should connect to database`() {
        val jdbcUrl = postgresContainer.getJdbcUrl()

        println(jdbcUrl)
    }
}