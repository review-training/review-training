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
}