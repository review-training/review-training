package nom.brunokarpo.review.quarkus.resources

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import java.util.UUID

@QuarkusTest
class ReviewResourcesTest {

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
                    .header("Location", Matchers.endsWith("/review?restaurant_id=$restId"))
                    .body("restaurantId", Matchers.equalTo(restId.toString()),
                            "average", Matchers.equalTo(5.0f),
                            "qtdReview", Matchers.equalTo(1))
    }

    @Test
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