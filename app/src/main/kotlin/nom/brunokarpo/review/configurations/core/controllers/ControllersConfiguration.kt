package nom.brunokarpo.review.configurations.core.controllers

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.controllers.ReviewsController
import nom.brunokarpo.review.core.usercases.CreateNewReviewUserCase
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class ControllersConfiguration {

    @Produces
    @DefaultBean
    fun reviewsController(createNewReviewUserCase: CreateNewReviewUserCase)
            = ReviewsController(createNewReviewUserCase)

}