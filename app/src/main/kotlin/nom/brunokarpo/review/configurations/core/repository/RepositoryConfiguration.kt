package nom.brunokarpo.review.configurations.core.repository

import io.agroal.api.AgroalDataSource
import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.repository.jdbc.ReviewRepositoryJdbc
import nom.brunokarpo.review.repository.jdbc.ReviewSummaryRepositoryJdbc
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.inject.Inject

@Dependent
class RepositoryConfiguration(
        @Inject val dataSource: AgroalDataSource
) {

    @Produces
    @DefaultBean
    fun reviewRepository() = ReviewRepositoryJdbc(dataSource)

    @Produces
    @DefaultBean
    fun reviewSummaryRepository() = ReviewSummaryRepositoryJdbc(dataSource)

}