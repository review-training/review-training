package nom.brunokarpo.review.repository.jdbc

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
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

    @BeforeEach
    internal fun setUp() {
        Flyway.configure()
                .dataSource(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword())
                .load()
                .migrate()
    }

    @Test
    internal fun `should connect to database`() {
        val jdbcUrl = postgresContainer.getJdbcUrl()

        println(jdbcUrl)
    }
}