package nom.brunokarpo.review.micronaut.configurations

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Value
import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.Session

@Factory
class JmsConfigurationFactory {

    @Value("\${activemq.broker-url}")
    private lateinit var brokerUrl: String

    @Value("\${activemq.user}")
    private lateinit var user: String

    @Value("\${activemq.password}")
    private lateinit var password: String

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val activeMQConnectionFactory = ActiveMQConnectionFactory(brokerUrl)
        activeMQConnectionFactory.userName = user
        activeMQConnectionFactory.password = password
        return activeMQConnectionFactory
    }

    @Bean
    fun jmsConnection(connectionFactory: ConnectionFactory): Connection {
        return connectionFactory.createConnection()
    }

    @Bean
    fun jmsSession(connection: Connection): Session {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    }

}