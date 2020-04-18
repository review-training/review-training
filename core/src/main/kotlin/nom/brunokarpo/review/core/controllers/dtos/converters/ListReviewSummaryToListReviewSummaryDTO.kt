package nom.brunokarpo.review.core.controllers.dtos.converters

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.core.model.ReviewSummary

class ListReviewSummaryToListReviewSummaryDTO: ModelDTOConverter<List<ReviewSummaryDTO>, List<ReviewSummary>> {

    override fun convert(model: List<ReviewSummary>): List<ReviewSummaryDTO> {
        return model.map { ReviewSummaryToReviewSummaryDTO().convert(it) }
    }
}