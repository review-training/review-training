package nom.brunokarpo.review.quarkus.messaging.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.messaging.consumers.messages.ReviewMessage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.jms.JMSContext
import javax.jms.TextMessage

@ApplicationScoped
class ReviewListener(
        private val reviewMessageConsumer: ReviewMessageConsumer,
        private val objectMapper: ObjectMapper,
        private val context: JMSContext
): Runnable {

    private val scheduler: ExecutorService = Executors.newSingleThreadExecutor()

    fun onStart(@Observes ev: StartupEvent) {
        scheduler.submit(this)
    }

    fun onStop(@Observes ev: ShutdownEvent) {
        scheduler.shutdown()
    }

    override fun run() {
        val consumer = context.createConsumer(context.createQueue("queue.NEW_REVEIW_QUEUE"))
        while (true) {
            val message = consumer.receive() ?: return
            val textMessage = message as TextMessage
            val reviewMessage = objectMapper.readValue(textMessage.text, ReviewMessage::class.java)
            reviewMessageConsumer.process(reviewMessage)
        }
    }

}