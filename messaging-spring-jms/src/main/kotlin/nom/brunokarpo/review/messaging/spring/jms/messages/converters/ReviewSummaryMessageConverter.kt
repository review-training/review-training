package nom.brunokarpo.review.messaging.spring.jms.messages.converters

import com.fasterxml.jackson.databind.ObjectMapper
import nom.brunokarpo.review.core.model.ReviewSummary
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.stereotype.Component
import javax.jms.Message
import javax.jms.Session
import javax.jms.TextMessage

@Component
class ReviewSummaryMessageConverter(
        private val mapper: ObjectMapper
): MessageConverter {

    override fun toMessage(reviewSummary: Any, session: Session): Message {
        val reviewSummaryTopic = if(reviewSummary is ReviewSummary) {
            reviewSummary
        } else {
            mapper.readValue(reviewSummary as String, ReviewSummary::class.java)
        }
        val payload = mapper.writeValueAsString(reviewSummaryTopic)
        val textMessage = session.createTextMessage()
        textMessage.text = payload
        return textMessage
    }

    override fun fromMessage(message: Message): Any {
        val textMessage = message as TextMessage
        val payload = textMessage.text

        return mapper.readValue(payload, ReviewSummary::class.java)
    }
}