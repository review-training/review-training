package nom.brunokarpo.review.resource

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.util.*

@QuarkusTest
class ReviewResourceTest {

    @Test
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
                    .header("Location", Matchers.endsWith("/review/$restId"))
                    .body("restaurantId", Matchers.equalTo(restId.toString()),
                            "average", Matchers.equalTo(5.0f),
                            "qtdReview", Matchers.equalTo(1))
    }
}