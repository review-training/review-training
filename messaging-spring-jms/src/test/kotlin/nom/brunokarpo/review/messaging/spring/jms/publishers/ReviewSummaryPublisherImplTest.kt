package nom.brunokarpo.review.messaging.spring.jms.publishers

import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.messaging.spring.jms.MessagingTestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random
import kotlin.test.assertNotNull

internal class ReviewSummaryPublisherImplTest: MessagingTestBase() {

    @Autowired
    private lateinit var sut: ReviewSummaryPublisher

    @Test
    internal fun `should publish review summary`() {
        val reviewSummary =
                ReviewSummary(restaurantId = UUID.randomUUID(),
                        average = BigDecimal.valueOf(Random.nextDouble(1.0, 5.0)),
                        qtdReview = Random.nextInt())
        sut.publish(reviewSummary)

        val message = jmsTemplate.receiveAndConvert("topic.REVIEW_RESUME_TOPIC")

        assertNotNull(message)
    }

}