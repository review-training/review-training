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

    @Test
    internal fun `should retrieve a paginated list of summaries`() {
        given()
            .request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("page", 0)
                .param("size", 5)
            .`when`()
                .get("/review:list")
            .then()
                .log().headers()
                .log().body()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("size", Matchers.equalTo(5),
                        "first", Matchers.equalTo(true),
                        "last", Matchers.equalTo(false),
                        "numberOfElements", Matchers.equalTo(5),
                        "totalPages", Matchers.equalTo(6),
                        "totalElements", Matchers.equalTo(26),
                        "content[0].id", Matchers.equalTo("5093fdf4-04fb-46ae-adb7-88848a2a978a"),
                        "content[0].average", Matchers.equalTo(3.2f),
                        "content[0].qtdReview", Matchers.equalTo(20),
                        "content[1].id", Matchers.equalTo("1ea36074-5880-4710-8943-d1ccb8ca6a18"),
                        "content[1].average", Matchers.equalTo(3.1f),
                        "content[1].qtdReview", Matchers.equalTo(19),
                        "content[2].id", Matchers.equalTo("cc2c3338-efe2-42f1-b424-76fb0ac2f624"),
                        "content[2].average", Matchers.equalTo(3.0f),
                        "content[2].qtdReview", Matchers.equalTo(22),
                        "content[3].id", Matchers.equalTo("3e8b1069-9241-4695-a901-e805a05633a9"),
                        "content[3].average", Matchers.equalTo(2.8f),
                        "content[3].qtdReview", Matchers.equalTo(18),
                        "content[4].id", Matchers.equalTo("edec1033-95ff-4e74-8fd1-c37114b32ea1"),
                        "content[4].average", Matchers.equalTo(2.7f),
                        "content[4].qtdReview", Matchers.equalTo(26))
    }
}