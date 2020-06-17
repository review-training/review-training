package nom.brunokarpo.review.micronaut.resources

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.micronaut.resources.dto.ReviewDTOResource
import nom.brunokarpo.review.micronaut.resources.dto.ReviewSummaryDTOResource
import java.net.URI

@Controller("/review")
class ReviewResources(
        private val reviewsController: ReviewsController
) {

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createReview(@Body reviewDTOResource: ReviewDTOResource): HttpResponse<ReviewSummaryDTOResource> {
        val created = reviewsController.createNew(reviewDTOResource.toModel())
        return HttpResponse.created(ReviewSummaryDTOResource(created), URI("/review/restaurant_id=${reviewDTOResource.restaurantId}"))
    }

}