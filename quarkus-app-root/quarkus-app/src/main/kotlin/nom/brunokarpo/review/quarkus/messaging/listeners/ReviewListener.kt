package nom.brunokarpo.review.quarkus.messaging.listeners

import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.quarkus.messaging.messages.ReviewJMSMessage
import javax.enterprise.context.ApplicationScoped
import javax.jms.ConnectionFactory
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session

@ApplicationScoped
class ReviewListener(
        private val reviewMessageConsumer: ReviewMessageConsumer,
        private val connectionFactory: ConnectionFactory
) {
   fun run() {
        try {
            val context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)
            val consumer = context.createConsumer(context.createQueue("queue.NEW_REVIEW_QUEUE"))
            while (true) {
                val message: Message? = consumer.receive() ?: return
                val review = message!!.getBody(ReviewJMSMessage::class.java)
                reviewMessageConsumer.process(review.asMessage())
            }
        } catch (ex: JMSException) {
            throw RuntimeException(ex)
        }
    }


}