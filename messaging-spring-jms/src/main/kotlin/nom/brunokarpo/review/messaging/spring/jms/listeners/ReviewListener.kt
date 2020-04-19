package nom.brunokarpo.review.messaging.spring.jms.listeners

import nom.brunokarpo.review.messaging.ReviewMessageConsumer
import nom.brunokarpo.review.messaging.spring.jms.messages.ReviewAMQMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class ReviewListener(
        private val reviewMessageConsumer: ReviewMessageConsumer
) {

    private val LOG: Logger = LoggerFactory.getLogger(ReviewListener::class.java)

    @JmsListener(destination = "queue.NEW_REVIEW_QUEUE")
    fun receive(message: ReviewAMQMessage) {
        LOG.info("Received message = '$message'")
        reviewMessageConsumer.process(message.asMessage())
    }

}