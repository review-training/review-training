package nom.brunokarpo.review.micronaut.resources

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import nom.brunokarpo.review.core.controllers.ReviewSummaryController
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.micronaut.resources.dto.PageDTOResource
import nom.brunokarpo.review.micronaut.resources.dto.ReviewSummaryDTOResource
import nom.brunokarpo.review.micronaut.resources.dto.converter.ReviewSummaryDTOToReviewSummaryDTOResourceConverter
import java.util.*

@Controller("/review")
class ReviewSummaryResources(
        private val reviewSummaryController: ReviewSummaryController
) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun retrieveSummaryByRestaurantId(@QueryValue("restaurant_id") restaurantId: UUID) : HttpResponse<ReviewSummaryDTOResource> {
        return HttpResponse.ok(ReviewSummaryDTOResource(reviewSummaryController.retrieveByRestaurantId(restaurantId)))
    }

    @Get("/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun retrieveSummaryList(@QueryValue("size") size: Int?, @QueryValue("page") page: Int?): HttpResponse<PageDTOResource<ReviewSummaryDTOResource>> {
        return HttpResponse.ok(PageDTOResource(reviewSummaryController.retrieveList(size ?: 10, page ?: 0)) {
            it.map { element -> ReviewSummaryDTOToReviewSummaryDTOResourceConverter().convert(element as ReviewSummaryDTO) }
        })
    }
}