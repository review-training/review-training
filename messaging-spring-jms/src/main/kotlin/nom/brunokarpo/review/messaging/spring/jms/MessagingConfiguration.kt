package nom.brunokarpo.review.messaging.spring.jms

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJms
class MessagingConfiguration