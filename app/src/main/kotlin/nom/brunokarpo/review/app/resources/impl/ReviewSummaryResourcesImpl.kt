package nom.brunokarpo.review.app.resources.impl

import nom.brunokarpo.review.app.resources.ReviewSummaryResources
import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import nom.brunokarpo.review.core.controllers.ReviewSummaryController
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class ReviewSummaryResourcesImpl(
        private val reviewSummaryController: ReviewSummaryController
): ReviewSummaryResources {

    override fun retrieveSummaryByRestaurantId(restaurantId: UUID)
            : ResponseEntity<ReviewSummaryDTOResource> {
        return ResponseEntity.ok(ReviewSummaryDTOResource(reviewSummaryController.retrieveByRestaurantId(restaurantId)))
    }

}