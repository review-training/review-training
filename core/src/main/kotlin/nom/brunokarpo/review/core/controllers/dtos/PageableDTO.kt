package nom.brunokarpo.review.core.controllers.dtos

import nom.brunokarpo.review.core.model.Pageable

data class PageableDTO<DTO>(
        var size: Int = 0,
        var page: Int = 0,
        var first: Boolean = true,
        var last: Boolean = true,
        var numberOfElements: Int = 0,
        var totalElements: Int = 0,
        var totalPages: Int = 0,
        var content: List<DTO> = emptyList()
) {

    constructor(pageable: Pageable<*>, converter: (any: List<*>) -> List<DTO>)
            : this(size = pageable.size, page = pageable.page, first = pageable.first, last = pageable.last,
                    numberOfElements = pageable.numberOfElements, totalElements = pageable.totalElements,
                    totalPages = pageable.totalPages,
                    content = converter(pageable.content))

}