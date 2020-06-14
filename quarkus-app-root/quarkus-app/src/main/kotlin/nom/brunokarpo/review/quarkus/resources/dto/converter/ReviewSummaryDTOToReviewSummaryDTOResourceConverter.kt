package nom.brunokarpo.review.quarkus.resources.dto.converter

import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO
import nom.brunokarpo.review.quarkus.resources.dto.ReviewSummaryDTOResource

class ReviewSummaryDTOToReviewSummaryDTOResourceConverter
    : DTOtoDTOResourceConverter<ReviewSummaryDTO, ReviewSummaryDTOResource> {

    override fun convert(dto: ReviewSummaryDTO): ReviewSummaryDTOResource {
        return ReviewSummaryDTOResource(restaurantId = dto.restaurantId, average = dto.average, qtdReview = dto.qtdReview)
    }
}