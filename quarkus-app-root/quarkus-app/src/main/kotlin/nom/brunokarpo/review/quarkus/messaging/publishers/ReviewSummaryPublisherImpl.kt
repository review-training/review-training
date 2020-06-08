package nom.brunokarpo.review.quarkus.messaging.publishers

import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.messaging.publishers.ReviewSummaryPublisherGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.jms.ConnectionFactory
import javax.jms.JMSContext
import javax.jms.Session

@ApplicationScoped
class ReviewSummaryPublisherImpl(
        private val connectionFactory: ConnectionFactory
) : ReviewSummaryPublisherGateway {

    private val log: Logger = LoggerFactory.getLogger(ReviewSummaryPublisherImpl::class.java)

    override fun publish(reviewSummary: ReviewSummary) {
        var context: JMSContext? = null
        try {
            context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)
            context.createProducer().send(context.createQueue("topic.REVIEW_RESUME_TOPIC"), reviewSummary.toString())
        } catch (ex: Throwable) {
            log.error("An error happened trying to send message to a queue: ${ex.message}", ex)
        } finally {
            context?.close()
        }
    }
}