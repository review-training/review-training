package nom.brunokarpo.review.core.controllers.dtos

import nom.brunokarpo.review.core.controllers.dtos.converters.ModelDTOConverter
import nom.brunokarpo.review.core.model.Pageable

data class PageableDTO<DTO, MODEL>(
        var size: Int = 0,
        var page: Int = 0,
        var first: Boolean = true,
        var last: Boolean = true,
        var numberOfElements: Int = 0,
        var totalPages: Int = 0,
        var content: List<DTO> = emptyList()
) {

    constructor(pageable: Pageable<MODEL>, converter: ModelDTOConverter<DTO, MODEL>)
            : this(size = pageable.size, page = pageable.page, first = pageable.first, last = pageable.last,
                    numberOfElements = pageable.numberOfElements, totalPages = pageable.totalPages,
                    content = pageable.content.map { model -> converter.convert(model) })

}