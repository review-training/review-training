package nom.brunokarpo.review.core.controllers

import nom.brunokarpo.review.core.controllers.dtos.ReviewDTO
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase

class ReviewsController(
        private val createNewReviewUserCase: CreateNewReviewUserCase
) {

    fun createNew(reviewDto: ReviewDTO)
            = ReviewSummaryDTO( createNewReviewUserCase.execute(reviewDto.toModel()) )

}
