package nom.brunokarpo.review.repository.jdbc

import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainerProvider
import javax.sql.DataSource

abstract class DatabaseTestBase {

    companion object {
        private val POSTGRES_CONTAINER = PostgreSQLContainerProvider()
                .newInstance()
                .withDatabaseName("review")
                .withUsername("review-app")
                .withPassword("review-app")

        lateinit var DATA_SOURCE: DataSource
    }

    init {
        POSTGRES_CONTAINER.start()

        val pgDs = PGSimpleDataSource()
        pgDs.setURL(POSTGRES_CONTAINER.getJdbcUrl())
        pgDs.user = POSTGRES_CONTAINER.getUsername()
        pgDs.password = POSTGRES_CONTAINER.getUsername()
        pgDs.databaseName = POSTGRES_CONTAINER.getDatabaseName()

        DATA_SOURCE = pgDs

        Flyway.configure()
                .dataSource(POSTGRES_CONTAINER.getJdbcUrl(), POSTGRES_CONTAINER.getUsername(), POSTGRES_CONTAINER.getPassword())
                .load()
                .migrate()
    }
}