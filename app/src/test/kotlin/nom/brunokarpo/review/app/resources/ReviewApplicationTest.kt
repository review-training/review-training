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
import org.testcontainers.containers.GenericContainer
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

        private val ACTIVEMQ_CONTAINER = KGenericContainer("webcenter/activemq")
                .withExposedPorts(61616)
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            POSTGRES_CONTAINER.start()
            ACTIVEMQ_CONTAINER.start()

            TestPropertyValues.of(
                    "spring.datasource.url=${POSTGRES_CONTAINER.getJdbcUrl()}",
                    "spring.datasource.username=${POSTGRES_CONTAINER.getUsername()}",
                    "spring.datasource.password=${POSTGRES_CONTAINER.getPassword()}",
                    "spring.activemq.broker-url=tcp://${ACTIVEMQ_CONTAINER.containerIpAddress}:${ACTIVEMQ_CONTAINER.getMappedPort(61616)}",
                    "spring.activemq.user=",
                    "spring.activemq.password="
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

class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)