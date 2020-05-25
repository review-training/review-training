package nom.brunokarpo.review.app.resources.dtos.converter

interface DTOtoDTOResourceConverter<in DTO, out DTO_RESOURCE> {

    fun convert(dto:DTO): DTO_RESOURCE
}