package nom.brunokarpo.review.app.resources

import io.restassured.RestAssured
import nom.brunokarpo.review.app.ReviewApplication
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.PostgreSQLContainerProvider
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ReviewApplication::class])
@ActiveProfiles("test")
@Testcontainers
@ExtendWith(SpringExtension::class)
abstract class ReviewApplicationTest {

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

    @LocalServerPort
    protected var port: Int? = null

    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port!!
    }
}