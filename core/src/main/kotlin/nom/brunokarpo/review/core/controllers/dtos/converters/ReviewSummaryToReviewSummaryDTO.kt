package nom.brunokarpo.review.core.controllers.dtos.converters

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.model.ReviewSummary

class ReviewSummaryToReviewSummaryDTO: ModelDTOConverter<ReviewSummaryDTO, ReviewSummary> {

    override fun convert(model: ReviewSummary)
            = ReviewSummaryDTO(restaurantId = model.restaurantId, qtdReview = model.qtdReview, average = model.average)

}