package nom.brunokarpo.review.app.resources.impl

import nom.brunokarpo.review.app.resources.ReviewSummaryResources
import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class ReviewSummaryResourcesImpl: ReviewSummaryResources {

    override fun retrieveSummaryByRestaurantId(restaurantId: UUID)
            : ResponseEntity<ReviewSummaryDTOResource> {
        return ResponseEntity.badRequest().build()
    }

}