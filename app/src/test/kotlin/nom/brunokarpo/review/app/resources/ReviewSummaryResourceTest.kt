package nom.brunokarpo.review.app.resources

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.util.*

@SqlGroup(
        Sql(value = ["/load-database.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        Sql(value = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class ReviewSummaryResourceTest: ReviewApplicationTest() {

    @Test
    internal fun `should retrieve the summary review of one restaurant`() {
        given()
            .request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("restaurant_id", UUID.fromString("cc2c3338-efe2-42f1-b424-76fb0ac2f624"))
            .`when`()
                .get("/review")
            .then()
                .log().headers()
                .log().body()
                .and()
                    .statusCode(HttpStatus.OK.value())
                    .contentType(ContentType.JSON)
                    .body("restaurantId", Matchers.equalTo("cc2c3338-efe2-42f1-b424-76fb0ac2f624"),
                        "average", Matchers.equalTo(3.0f),
                        "qtdReview", Matchers.equalTo(22))
    }
}