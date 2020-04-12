package nom.brunokarpo.review.app.resources

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.util.*

@SqlGroup(
        Sql(value = ["/load-database.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        Sql(value = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class ReviewResourcesTest: ReviewApplicationTest() {

    @Test
    internal fun `should create new review`() {
        val restId = UUID.randomUUID()!!
        val userId = UUID.randomUUID()!!
        val orderId = UUID.randomUUID()!!
        val review = 5
        val body
                = mapOf("restaurantId" to restId, "userId" to userId,
                "orderId" to orderId, "review" to review)

        RestAssured.given()
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

    @Test
    internal fun `should create new review and recalculate summary`() {
        val restId = UUID.fromString("b975588b-28e4-4bbd-b512-646da66e58ec")
        val userId = UUID.randomUUID()!!
        val orderId = UUID.randomUUID()!!
        val review = 5
        val body
                = mapOf("restaurantId" to restId, "userId" to userId,
                "orderId" to orderId, "review" to review)

        RestAssured.given()
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
                            "average", Matchers.equalTo(2.3f),
                            "qtdReview", Matchers.equalTo(25))
    }
}