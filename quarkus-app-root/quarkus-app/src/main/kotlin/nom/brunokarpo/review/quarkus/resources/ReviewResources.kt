package nom.brunokarpo.review.quarkus.resources

import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.quarkus.resources.dto.ReviewDTOResource
import nom.brunokarpo.review.quarkus.resources.dto.ReviewSummaryDTOResource
import java.math.BigDecimal
import java.net.URI
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/review")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class ReviewResources(
        @Inject
        private val reviewController: ReviewsController
) {

    @POST
    fun createReview(dto: ReviewDTOResource): Response {
        val reviewSummaryDTOResource = ReviewSummaryDTOResource(reviewController.createNew(dto.toModel()))
        return Response.created(URI("/review?restaurant_id=${reviewSummaryDTOResource.restaurantId}"))
                .entity(reviewSummaryDTOResource).build()
    }

}