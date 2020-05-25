package nom.brunokarpo.review.messaging.spring.jms.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.messaging.spring.jms.messages.ReviewAMQMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import javax.jms.Message
import javax.jms.TextMessage

@Component
class ReviewListener(
        private val reviewMessageConsumer: ReviewMessageConsumer,
        private val objectMapper: ObjectMapper
) {

    private val LOG: Logger = LoggerFactory.getLogger(ReviewListener::class.java)

    @JmsListener(destination = "queue.NEW_REVIEW_QUEUE")
    fun receive(message: Message) {
        LOG.info("Received message = '$message'")

        val textMessage = message as TextMessage
        val payload = textMessage.text

        val reviewAMQMessage = objectMapper.readValue(payload, ReviewAMQMessage::class.java)

        reviewMessageConsumer.process(reviewAMQMessage.asMessage())
    }

}