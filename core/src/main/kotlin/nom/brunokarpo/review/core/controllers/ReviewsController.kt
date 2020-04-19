package nom.brunokarpo.review.core.controllers

import nom.brunokarpo.review.core.controllers.dtos.ReviewDTO
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.usercases.CreateNewReviewUseCase

class ReviewsController(
        private val createNewReviewUseCase: CreateNewReviewUseCase
) {

    fun createNew(reviewDto: ReviewDTO)
            = ReviewSummaryDTO( createNewReviewUseCase.execute(reviewDto.toModel()) )

}
