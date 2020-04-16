package nom.brunokarpo.review.core.controllers

import nom.brunokarpo.review.core.controllers.dtos.PageableDTO
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.model.ReviewSummary
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewByRestaurantIdUseCase
import nom.brunokarpo.review.core.usercases.RetrieveSummaryReviewListPaginatedUseCase
import java.util.*

class ReviewSummaryController(
        private val retrieveSummaryReviewByRestaurantIdUseCase: RetrieveSummaryReviewByRestaurantIdUseCase,
        private val retrieveSummaryReviewListPaginatedUseCase: RetrieveSummaryReviewListPaginatedUseCase
) {

    fun retrieveByRestaurantId(restaurantId: UUID): ReviewSummaryDTO {
        return ReviewSummaryDTO(retrieveSummaryReviewByRestaurantIdUseCase.execute(restaurantId))
    }

    fun retrieveList(size: Int, page: Int): PageableDTO<ReviewSummaryDTO, ReviewSummary> {
        return PageableDTO(retrieveSummaryReviewListPaginatedUseCase.execute(size = size, page = page))
    }

}
