package nom.brunokarpo.review.app.resources.dtos

class PageDTOResource<T> {
    var size: Int = 0
    var first: Boolean = true
    var last: Boolean = true
    var numberOfElements: Int = 0
    var totalPages: Int = 0
    var content: List<T> = emptyList()
}