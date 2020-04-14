package nom.brunokarpo.review.core.model

class Pageable<T>(
        var size: Int = 0,
        var page: Int = 0,
        var first: Boolean = true,
        var last: Boolean = true,
        var numberOfElements: Int = 0,
        var totalPages: Int = 0,
        var content: List<T> = emptyList()
) {
}