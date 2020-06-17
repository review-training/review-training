package nom.brunokarpo.review.micronaut.messaging.listeners

import io.micronaut.scheduling.annotation.Scheduled
import javax.inject.Singleton

@Singleton
class JmsListenerJob(
        private val reviewListenerImpl: ReviewListenerImpl
){

    @Scheduled(fixedDelay = "1s")
    fun listen() {
        reviewListenerImpl.run()
    }

}