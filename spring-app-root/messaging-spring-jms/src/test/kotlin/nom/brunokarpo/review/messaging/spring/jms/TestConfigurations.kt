package nom.brunokarpo.review.messaging.spring.jms

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfigurations {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }

}