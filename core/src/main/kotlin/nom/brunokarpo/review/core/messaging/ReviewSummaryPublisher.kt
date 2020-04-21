package nom.brunokarpo.review.core.messaging

import nom.brunokarpo.review.core.model.ReviewSummary

interface ReviewSummaryPublisher {

    fun publish(reviewSummary: ReviewSummary)

}