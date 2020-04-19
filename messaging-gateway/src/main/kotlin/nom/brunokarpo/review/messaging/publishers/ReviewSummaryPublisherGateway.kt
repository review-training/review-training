package nom.brunokarpo.review.messaging.publishers

import nom.brunokarpo.review.core.messaging.ReviewSummaryPublisher
import nom.brunokarpo.review.core.model.ReviewSummary

interface ReviewSummaryPublisherGateway: ReviewSummaryPublisher {

    override fun publish(reviewSummary: ReviewSummary)

}