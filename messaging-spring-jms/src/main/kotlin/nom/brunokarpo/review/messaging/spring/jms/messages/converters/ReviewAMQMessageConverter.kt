package nom.brunokarpo.review.messaging.spring.jms.messages.converters

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.messaging.spring.jms.messages.ReviewAMQMessage
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.stereotype.Component
import javax.jms.Message
import javax.jms.Session
import javax.jms.TextMessage

@Component
class ReviewAMQMessageConverter(
        private val objectMapper: ObjectMapper
): MessageConverter {

    override fun toMessage(reviewMessage: Any, session: Session): Message {
        val reviewAMQMessage = if (reviewMessage is ReviewAMQMessage) {
            reviewMessage
        } else if (reviewMessage is String) {
            objectMapper.readValue(reviewMessage, ReviewAMQMessage::class.java)
        } else {
            ReviewAMQMessage()
        }
        val payload: String = objectMapper.writeValueAsString(reviewAMQMessage)
        val textMessage = session.createTextMessage()
        textMessage.text = payload
        return textMessage
    }

    override fun fromMessage(message: Message): Any {
        val textMessage = message as TextMessage
        val payload = textMessage.text

        return try {
            objectMapper.readValue(payload, ReviewAMQMessage::class.java)
        } catch (e: Exception) {
            ReviewAMQMessage()
        }
    }
}