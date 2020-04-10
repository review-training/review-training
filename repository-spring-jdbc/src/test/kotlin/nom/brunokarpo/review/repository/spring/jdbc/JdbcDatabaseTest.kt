package nom.brunokarpo.review.repository.spring.jdbc

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainerProvider
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = [JdbcDatabaseConfiguration::class])
@ActiveProfiles("test")
@Testcontainers
abstract class JdbcDatabaseTest {

    companion object {
        @Container
        private val POSTGRES_CONTAINER = PostgreSQLContainerProvider()
                .newInstance()
                .withDatabaseName("review")
                .withUsername("review-app")
                .withPassword("review-app")
    }

    init {
        POSTGRES_CONTAINER.start()

        System.setProperty("spring.datasource.url", POSTGRES_CONTAINER.getJdbcUrl())
        System.setProperty("spring.datasource.username", POSTGRES_CONTAINER.getUsername())
        System.setProperty("spring.datasource.password", POSTGRES_CONTAINER.getPassword())
    }

}