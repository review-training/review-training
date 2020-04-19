package nom.brunokarpo.review.messaging.spring.jms.listeners

import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import nom.brunokarpo.review.messaging.consumers.ReviewMessageConsumer
import nom.brunokarpo.review.messaging.consumers.messages.ReviewMessage
import nom.brunokarpo.review.messaging.spring.jms.MessagingTestBase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import java.nio.charset.Charset
import java.nio.file.Files
import java.util.*
import kotlin.test.assertEquals

class ReviewListenerTest: MessagingTestBase() {

    @MockkBean
    lateinit var reviewMessageConsumer: ReviewMessageConsumer

    private lateinit var slot: CapturingSlot<ReviewMessage>

    @BeforeEach
    internal fun setUp() {
        slot = slot()

        every {
            reviewMessageConsumer.process(capture(slot))
        } just Runs

        val json = Files.readAllBytes( ClassPathResource("new_review_message.json").file.toPath() ).toString(Charset.defaultCharset())
        jmsTemplate.convertAndSend("queue.NEW_REVIEW_QUEUE", json)

        Thread.sleep(500)
    }

    @Test
    internal fun `should process message`() {
        verify {
            reviewMessageConsumer.process(any())
        }
    }

    @Test
    internal fun `should process message with correct restaurant id`() {
        assertEquals(UUID.fromString("b5372d84-0085-4dbe-bbb3-5a1f04ae2932"), slot.captured.restaurantId)
    }

    @Test
    internal fun `should process message with correct user id`() {
        assertEquals(UUID.fromString("82b00ea1-5157-4456-a233-c58423a75475"), slot.captured.userId)
    }

    @Test
    internal fun `should process message with correct order id`() {
        assertEquals(UUID.fromString("46d9dc5f-71a6-4162-8c98-47168cc8a8dc"), slot.captured.orderId)
    }

    @Test
    internal fun `should process message with correct review`() {
        assertEquals(4, slot.captured.review)
    }
}