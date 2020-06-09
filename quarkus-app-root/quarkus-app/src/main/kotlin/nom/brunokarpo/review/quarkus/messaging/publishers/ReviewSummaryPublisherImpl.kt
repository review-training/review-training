package nom.brunokarpo.review.quarkus.messaging.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.messaging.publishers.ReviewSummaryPublisherGateway
import javax.enterprise.context.ApplicationScoped
import javax.jms.JMSContext

@ApplicationScoped
class ReviewSummaryPublisherImpl(
        private val context: JMSContext,
        private val objectMapper: ObjectMapper
): ReviewSummaryPublisherGateway {

    override fun publish(reviewSummary: ReviewSummary) {
        val topic = context.createTopic("topic.REVIEW_RESUME_TOPIC")
        val message = context.createTextMessage(objectMapper.writeValueAsString(reviewSummary))
        val producer = context.createProducer()
        producer.send(topic, message)
    }
}