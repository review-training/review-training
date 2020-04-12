package nom.brunokarpo.review.app.resources

import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/review")
interface ReviewSummaryResources {

    @GetMapping
    fun retrieveSummaryByRestaurantId(@RequestParam("restaurant_id") restaurantId: UUID)
            : ResponseEntity<ReviewSummaryDTOResource>

}