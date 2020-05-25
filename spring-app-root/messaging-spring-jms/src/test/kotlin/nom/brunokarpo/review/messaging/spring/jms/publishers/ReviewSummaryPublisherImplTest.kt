package nom.brunokarpo.review.messaging.spring.jms.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.messaging.spring.jms.MessagingTestBase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@Suppress("UNCHECKED_CAST")
internal class ReviewSummaryPublisherImplTest: MessagingTestBase() {

    companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val AVERAGE = BigDecimal.valueOf(Random.nextDouble())!!
        val QTD_REVIEW = Random.nextInt()
    }

    @Autowired
    private lateinit var sut: ReviewSummaryPublisher

    @Autowired
    private lateinit var mapper: ObjectMapper

    private var message: Any? = null

    @BeforeEach
    internal fun setUp() {
        val reviewSummary =
                ReviewSummary(restaurantId = RESTAURANT_ID,
                        average = AVERAGE,
                        qtdReview = QTD_REVIEW)
        sut.publish(reviewSummary)

        message = jmsTemplate.receiveAndConvert("topic.REVIEW_RESUME_TOPIC")
    }

    @Test
    internal fun `should publish review summary`() {
        assertNotNull(message)
    }

    @Test
    internal fun `should publish review with correct restaurant id`() {
        val result: Map<String, Any> = mapper.readValue(message as String, Map::class.java) as Map<String, Any>

        assertEquals(RESTAURANT_ID.toString(), result["restaurantId"])
    }

    @Test
    internal fun `should publish review with correct average`() {
        val result: Map<String, Any> = mapper.readValue(message as String, Map::class.java) as Map<String, Any>

        assertEquals(AVERAGE.toDouble(), result["average"])
    }

    @Test
    internal fun `should publish review with correct qtt review`() {
        val result: Map<String, Any> = mapper.readValue(message as String, Map::class.java) as Map<String, Any>

        assertEquals(QTD_REVIEW, result["qtdReview"])
    }
}