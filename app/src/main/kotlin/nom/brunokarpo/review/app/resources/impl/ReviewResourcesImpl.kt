package nom.brunokarpo.review.app.resources.impl

import nom.brunokarpo.review.app.resources.ReviewResources
import nom.brunokarpo.review.app.resources.dtos.ReviewDTOResource
import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import nom.brunokarpo.review.core.controllers.ReviewsController
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.net.URI

@Component
class ReviewResourcesImpl(
        private val reviewsController: ReviewsController
): ReviewResources {

    override fun createReview(dto: ReviewDTOResource): ResponseEntity<ReviewSummaryDTOResource> {
        val result = ReviewSummaryDTOResource(reviewsController.createNew(dto.toModel()))
        return ResponseEntity.created(URI("/review/${result.restaurantId}")).body(result)
    }
}