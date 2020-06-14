package nom.brunokarpo.review.quarkus.configurations

import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.arc.DefaultBean
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.JMSContext
import javax.jms.Session

@Dependent
class JmsConfiguration(
        private val connectionFactory: ConnectionFactory
) {

    @Produces
    @DefaultBean
    fun jmsConnection(): Connection
            = connectionFactory.createConnection()

    @Produces
    @DefaultBean
    fun jmsContext(): JMSContext
            = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)

    @Produces
    @DefaultBean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}