package nom.brunokarpo.review.quarkus.resources

import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)

@Testcontainers
@QuarkusTest
class ReviewResourcesTest {


    companion object {
        @Container
        private val ACTIVEMQ_CONTAINER = KGenericContainer("vromero/activemq-artemis:latest-alpine")
                .withExposedPorts(61616)
                .withEnv("ARTEMIS_USERNAME", "admin")
                .withEnv("ARTEMIS_PASSWORD", "admin")
                // Below should not be used - Function is deprecated and for simplicity of test , You should override your properties at runtime
                // By the way, Quarkus don't override properties at runtime
                .withCreateContainerCmdModifier {
                    it.withHostName("localhost")
                            .withPortBindings(PortBinding(Ports.Binding.bindPort(61616), ExposedPort(61616)))
                }
    }

    @Test
    @Disabled
    internal fun `should create new review`() {
        val restId = UUID.randomUUID()!!
        val userId = UUID.randomUUID()!!
        val orderId = UUID.randomUUID()!!
        val review = 5
        val body
                = mapOf("restaurantId" to restId, "userId" to userId,
                "orderId" to orderId, "review" to review)

        given()
            .request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
            .`when`()
                .post("/review")
            .then()
                .log().headers()
                .and().log().body()
                .and()
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    .header("Location", Matchers.endsWith("/review?restaurant_id=$restId"))
                    .body("restaurantId", Matchers.equalTo(restId.toString()),
                            "average", Matchers.equalTo(5.0f),
                            "qtdReview", Matchers.equalTo(1))
    }

    @Test
    @Disabled("I need to load sql script before and after test execution")
    internal fun `should create new review and recalculate summary`() {
        val restId = UUID.fromString("b975588b-28e4-4bbd-b512-646da66e58ec")
        val userId = UUID.randomUUID()!!
        val orderId = UUID.randomUUID()!!
        val review = 5
        val body
                = mapOf("restaurantId" to restId, "userId" to userId,
                "orderId" to orderId, "review" to review)

        given()
            .request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
            .`when`()
                .post("/review")
            .then()
                .log().headers()
                .and().log().body()
                .and()
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    .header("Location", Matchers.endsWith("/review?restaurant_id=$restId"))
                    .body("restaurantId", Matchers.equalTo(restId.toString()),
                            "average", Matchers.equalTo(2.3f),
                            "qtdReview", Matchers.equalTo(25))
    }
}