package nom.brunokarpo.review.micronaut.messaging.publishers

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.ReviewSummary
import javax.inject.Singleton
import javax.jms.Session

@Singleton
class ReviewSummaryPublisherImpl(
        private val session: Session,
        private val objectMapper: ObjectMapper
): ReviewSummaryPublisher {


    override fun publish(reviewSummary: ReviewSummary) {
        val topic = session.createTopic("topic.REVIEW_RESUME_TOPIC")
        val message = session.createTextMessage(objectMapper.writeValueAsString(reviewSummary))
        val producer = session.createProducer(topic)
        producer.send(message)
    }


}