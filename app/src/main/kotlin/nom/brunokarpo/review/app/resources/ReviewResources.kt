package nom.brunokarpo.review.app.resources

import nom.brunokarpo.review.app.resources.dtos.ReviewDTOResource
import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/review")
interface ReviewResources {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createReview(@RequestBody dto: ReviewDTOResource): ResponseEntity<ReviewSummaryDTOResource>

}