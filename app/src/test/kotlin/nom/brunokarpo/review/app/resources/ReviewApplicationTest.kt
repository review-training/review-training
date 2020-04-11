package nom.brunokarpo.review.app.resources

import io.restassured.RestAssured
import nom.brunokarpo.review.app.ReviewApplication
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.PostgreSQLContainerProvider


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ReviewApplication::class])
@ActiveProfiles("test")
@ContextConfiguration(initializers = [ReviewApplicationTest.Initializer::class])
@ExtendWith(SpringExtension::class)
abstract class ReviewApplicationTest {

    companion object {
        private val POSTGRES_CONTAINER = PostgreSQLContainerProvider()
                .newInstance()
                .withDatabaseName("review")
                .withUsername("review-app")
                .withPassword("review-app")
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            POSTGRES_CONTAINER.start()

            TestPropertyValues.of(
                    "spring.datasource.url=${POSTGRES_CONTAINER.getJdbcUrl()}",
                    "spring.datasource.username=${POSTGRES_CONTAINER.getUsername()}",
                    "spring.datasource.password=${POSTGRES_CONTAINER.getPassword()}"
            ).applyTo(configurableApplicationContext.environment)
        }
    }

    @LocalServerPort
    protected var port: Int? = null

    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port!!
        RestAssured.basePath = "/review"
    }
}