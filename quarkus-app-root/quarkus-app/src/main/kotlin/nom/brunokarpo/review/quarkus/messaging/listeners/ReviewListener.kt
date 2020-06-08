package nom.brunokarpo.review.quarkus.messaging.listeners

import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.quarkus.messaging.messages.ReviewJMSMessage
import java.lang.RuntimeException
import java.util.concurrent.Executors
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.jms.ConnectionFactory
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.Session

@ApplicationScoped
class ReviewListener(
        private val reviewMessageConsumer: ReviewMessageConsumer,
        private val connectionFactory: ConnectionFactory
): Runnable {

    private val scheduler = Executors.newSingleThreadExecutor()

    fun onStart(@Observes ev: StartupEvent) {
        scheduler.submit(this)
    }

    fun onStop(@Observes ev: ShutdownEvent) {
        scheduler.shutdown()
    }

    override fun run() {
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