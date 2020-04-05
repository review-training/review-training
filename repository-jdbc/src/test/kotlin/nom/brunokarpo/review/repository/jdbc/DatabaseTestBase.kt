package nom.brunokarpo.review.repository.jdbc

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainerProvider
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import javax.sql.DataSource

@Testcontainers
abstract class DatabaseTestBase {

    @Container
    val postgresContainer = PostgreSQLContainerProvider()
            .newInstance()
            .withDatabaseName("review")
            .withUsername("review-app")
            .withPassword("review-app")

    internal lateinit var dataSource: DataSource

    @BeforeEach
    internal fun init() {
        Flyway.configure()
                .dataSource(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword())
                .load()
                .migrate()

        val pgDs = PGSimpleDataSource()
        pgDs.setURL(postgresContainer.getJdbcUrl())
        pgDs.user = postgresContainer.getUsername()
        pgDs.password = postgresContainer.getUsername()
        pgDs.databaseName = postgresContainer.getDatabaseName()

        dataSource = pgDs
    }

}