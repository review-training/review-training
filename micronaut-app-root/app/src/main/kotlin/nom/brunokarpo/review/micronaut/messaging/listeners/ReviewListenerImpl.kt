package nom.brunokarpo.review.micronaut.messaging.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.micronaut.messaging.messages.ReviewJMSMessage
import javax.inject.Singleton
import javax.jms.Session
import javax.jms.TextMessage

@Singleton
class ReviewListenerImpl(
        private val reviewMessageConsumer: ReviewMessageConsumer,
        private val objectMapper: ObjectMapper,
        private val session: Session
) {

    fun run() {
        val consumer = session.createConsumer(session.createQueue("queue.NEW_REVIEW_QUEUE"))
        try {
            val message = consumer.receive() ?: return
            val textMessage = message as TextMessage
            val reviewMessage = objectMapper.readValue(textMessage.text, ReviewJMSMessage::class.java)
            reviewMessageConsumer.process(reviewMessage.asMessage())
        } finally {
            consumer.close()
        }
    }

}