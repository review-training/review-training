package nom.brunokarpo.review.core.controllers.dtos.converters

@FunctionalInterface
interface ModelDTOConverter<out DTO, in MODEL> {

    fun convert(model: MODEL): DTO
}