package nom.brunokarpo.review.resource

import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.resource.dtos.ReviewDTOResource
import nom.brunokarpo.review.resource.dtos.ReviewSummaryDTOResource
import java.net.URI
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/review")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ReviewResource(
        @Inject val reviewsController: ReviewsController
) {

    @POST
    fun createReview(reviewDTOResource: ReviewDTOResource): Response = Response
                .created(URI("/review/${reviewDTOResource.restaurantId}"))
                .entity(ReviewSummaryDTOResource(reviewsController.createNew(reviewDTOResource.toReviewDTO())))
                .build()
}