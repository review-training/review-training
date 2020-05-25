package nom.brunokarpo.review.app.resources.dtos.converter

import nom.brunokarpo.review.app.resources.dtos.ReviewSummaryDTOResource
import nom.brunokarpo.review.core.controllers.dtos.ReviewSummaryDTO

class ReviewSummaryDTOToReviewSummaryDTOResourceConverter
    : DTOtoDTOResourceConverter<ReviewSummaryDTO, ReviewSummaryDTOResource> {

    override fun convert(dto: ReviewSummaryDTO): ReviewSummaryDTOResource {
        return ReviewSummaryDTOResource(restaurantId = dto.restaurantId, average = dto.average, qtdReview = dto.qtdReview)
    }
}