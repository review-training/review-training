package nom.brunokarpo.review.core.model

import kotlin.math.ceil

class Pageable<T>(
        var size: Int = 0,
        var page: Int = 0,
        var first: Boolean = true,
        var last: Boolean = true,
        var numberOfElements: Int = 0,
        var totalPages: Int = 0,
        var content: List<T> = emptyList()
) {

    constructor(size: Int, page: Int, count: Int, content: List<T>)
            : this(size = size, page = page, first = page == 0, last = size * (page + 1) >= count,
            numberOfElements = count, totalPages = ceil(count.toDouble() / size).toInt(),
            content = content)
}