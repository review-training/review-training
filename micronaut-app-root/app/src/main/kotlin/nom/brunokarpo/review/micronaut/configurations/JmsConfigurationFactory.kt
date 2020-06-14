package nom.brunokarpo.review.micronaut.configurations

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.Session

@Factory
class JmsConfigurationFactory {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return ActiveMQConnectionFactory()
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