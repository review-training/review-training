package nom.brunokarpo.review.messaging.spring.jms

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.GenericContainer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = [MessagingConfiguration::class])
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestConfigurations::class],
        initializers = [MessagingTestBase.Initializer::class])
abstract class MessagingTestBase {

    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    companion object {
        private val ACTIVEMQ_CONTAINER = KGenericContainer("webcenter/activemq")
                .withExposedPorts(61616)
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            ACTIVEMQ_CONTAINER.start()

            TestPropertyValues.of(
                    "spring.activemq.broker-url=tcp://${ACTIVEMQ_CONTAINER.containerIpAddress}:61616",
                    "spring.activemq.user=",
                    "spring.activemq.password="
            )
        }
    }
}

class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)