package nom.brunokarpo.review.app.resources

import nom.brunokarpo.review.app.resources.dtos.PageDTOResource
import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/review")
interface ReviewSummaryResources {

    @GetMapping
    fun retrieveSummaryByRestaurantId(@RequestParam("restaurant_id") restaurantId: UUID)
            : ResponseEntity<ReviewSummaryDTOResource>

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveSummaryList(@RequestParam("size") size: Int = 10, @RequestParam("page") page: Int = 0)
            : PageDTOResource<ReviewSummaryDTOResource>
}