package nom.brunokarpo.review.app.resources.dtos

import nom.brunokarpo.review.core.controllers.dtos.PageableDTO

data class PageDTOResource<T> (
        var size: Int = 0,
        var page: Int = 0,
        var first: Boolean = true,
        var last: Boolean = true,
        var numberOfElements: Int = 0,
        var totalElements: Int = 0,
        var totalPages: Int = 0,
        var content: List<T> = emptyList()
) {

    constructor(pageable: PageableDTO<*>, converter: (any: List<*>) -> List<T>)
            : this(size = pageable.size, page = pageable.page, first = pageable.first, last = pageable.last,
            numberOfElements = pageable.numberOfElements, totalElements = pageable.totalElements,
            totalPages = pageable.totalPages, content = converter(pageable.content))
}