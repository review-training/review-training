package nom.brunokarpo.review.repository.jdbc

import org.apache.ibatis.jdbc.ScriptRunner
import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainerProvider
import java.io.BufferedReader
import java.io.FileReader
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

    fun loadDatabase() {
        val scriptRunner = ScriptRunner(DATA_SOURCE.connection)
        val reader = BufferedReader(FileReader(System.getProperty("user.dir") + "/src/test/resources/database/load-database.sql"))
        scriptRunner.runScript(reader)
    }

    fun cleanDatabase() {
        val scriptRunner = ScriptRunner(DATA_SOURCE.connection)
        val reader = BufferedReader(FileReader(System.getProperty("user.dir") + "/src/test/resources/database/clean-database.sql"))
        scriptRunner.runScript(reader)
    }
}