package nom.brunokarpo.review.messaging.spring.jms.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.messaging.publishers.ReviewSummaryPublisherGateway
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

@Component
class ReviewSummaryPublisherImpl(
        private val jmsTemplate: JmsTemplate,
        private val objectMapper: ObjectMapper
): ReviewSummaryPublisherGateway {

    override fun publish(reviewSummary: ReviewSummary) {
        val message = objectMapper.writeValueAsString(reviewSummary)
        jmsTemplate.convertAndSend("topic.REVIEW_RESUME_TOPIC", message)
    }
}