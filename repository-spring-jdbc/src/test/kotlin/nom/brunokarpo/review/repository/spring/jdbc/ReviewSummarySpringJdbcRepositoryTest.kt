package nom.brunokarpo.review.repository.spring.jdbc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.math.BigDecimal
import java.util.*

@SqlGroup(
        Sql(value = ["/load-database.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        Sql(value = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class ReviewSummarySpringJdbcRepositoryTest: DatabaseTestBase() {

    @Autowired
    private lateinit var sut: ReviewSummarySpringJdbcRepository

    @Test
    internal fun `should retrieve review summary by restaurant id`() {
        val restaurantId = UUID.fromString("811bfc9f-4ac6-496c-ac1a-566de4974bd0")
        
        val result = sut.getByRestaurantId(restaurantId)
        
        assertThat(result).isNotNull
        assertThat(result!!.restaurantId).isEqualTo(restaurantId)
        assertThat(result.qtdReview).isEqualTo(520)
        assertThat(result.average).isEqualTo(BigDecimal.valueOf(4.9))
    }

    @Test
    internal fun `should retrieve null when restaurant id does not exist`() {
        val restaurantId = UUID.randomUUID()

        val result = sut.getByRestaurantId(restaurantId)

        assertThat(result).isNull()
    }

    @Test
    internal fun `should retrieve list of summary review`() {
        val result = sut.list(size = 5, page = 0)

        assertThat(result.page).isEqualTo(0)
        assertThat(result.size).isEqualTo(5)
        assertThat(result.first).isTrue()
        assertThat(result.last).isFalse()
        assertThat(result.numberOfElements).isEqualTo(14)
        assertThat(result.totalPages).isEqualTo(3)

        assertThat(result.content[0].restaurantId).isEqualTo(UUID.fromString("811bfc9f-4ac6-496c-ac1a-566de4974bd0"))
        assertThat(result.content[0].average).isEqualTo(BigDecimal.valueOf(4.9))
        assertThat(result.content[0].qtdReview).isEqualTo(520)
        assertThat(result.content[1].restaurantId).isEqualTo(UUID.fromString("5c5749a5-e0e3-42a0-9d26-a428eb2e5344"))
        assertThat(result.content[1].average).isEqualTo(BigDecimal.valueOf(4.5))
        assertThat(result.content[1].qtdReview).isEqualTo(4)
        assertThat(result.content[2].restaurantId).isEqualTo(UUID.fromString("50227b7d-1e79-41b0-853d-4b658918a863"))
        assertThat(result.content[2].average).isEqualTo(BigDecimal.valueOf(3.5))
        assertThat(result.content[2].qtdReview).isEqualTo(4)
        assertThat(result.content[3].restaurantId).isEqualTo(UUID.fromString("e5bd0299-c1ba-4b1e-bfc1-5dce34afbb77"))
        assertThat(result.content[3].average).isEqualTo(BigDecimal.valueOf(3.3))
        assertThat(result.content[3].qtdReview).isEqualTo(7)
        assertThat(result.content[4].restaurantId).isEqualTo(UUID.fromString("a10bf534-9f7b-4f79-bfb9-0266ea8d3e81"))
        assertThat(result.content[4].average).isEqualTo(BigDecimal.valueOf(3.0))
        assertThat(result.content[4].qtdReview).isEqualTo(7)
    }
}