package nom.brunokarpo.review.messaging.spring.jms.publishers

import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.messaging.spring.jms.MessagingTestBase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertNotNull

internal class ReviewSummaryPublisherImplTest: MessagingTestBase() {

    companion object {
        val RESTAURANT_ID = UUID.randomUUID()!!
        val AVERAGE = BigDecimal.valueOf(Random.nextDouble())!!
        val QTD_REVIEW = Random.nextInt()!!
    }

    @Autowired
    private lateinit var sut: ReviewSummaryPublisher

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

}