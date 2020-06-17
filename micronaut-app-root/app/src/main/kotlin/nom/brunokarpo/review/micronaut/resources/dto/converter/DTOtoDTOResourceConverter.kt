package nom.brunokarpo.review.micronaut.resources.dto.converter

interface DTOtoDTOResourceConverter<in DTO, out DTO_RESOURCE> {

    fun convert(dto:DTO): DTO_RESOURCE
}