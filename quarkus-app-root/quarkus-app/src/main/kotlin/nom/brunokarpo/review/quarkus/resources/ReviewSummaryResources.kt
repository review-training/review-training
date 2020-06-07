package nom.brunokarpo.review.quarkus.resources

import nom.brunokarpo.review.core.controllers.ReviewSummaryController
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.quarkus.resources.dto.PageDTOResource
import nom.brunokarpo.review.quarkus.resources.dto.ReviewSummaryDTOResource
import nom.brunokarpo.review.quarkus.resources.dto.converter.ReviewSummaryDTOToReviewSummaryDTOResourceConverter
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/review")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class ReviewSummaryResources(
        private val reviewSummaryController: ReviewSummaryController
) {

    @GET
    fun retrieveSummaryByRestaurantId(@QueryParam("restaurant_id") restaurantId: UUID): Response {
        return Response.ok(ReviewSummaryDTOResource(reviewSummaryController.retrieveByRestaurantId(restaurantId))).build()
    }

    @GET
    @Path("/list")
    fun retrieveSummaryList(size: Int?, page: Int?): Response {
        return Response.ok(PageDTOResource(reviewSummaryController.retrieveList(size ?: 10, page ?: 0)) {
            it.map { element -> ReviewSummaryDTOToReviewSummaryDTOResourceConverter().convert(element as ReviewSummaryDTO) }
        }).build()
    }


}