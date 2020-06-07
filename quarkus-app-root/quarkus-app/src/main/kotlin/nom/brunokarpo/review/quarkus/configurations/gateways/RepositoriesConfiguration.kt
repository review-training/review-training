package nom.brunokarpo.review.quarkus.configurations.gateways

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import nom.brunokarpo.review.quarkus.repositories.ReviewRepositoryJdbc
import nom.brunokarpo.review.quarkus.repositories.ReviewSummaryRepositoryJdbc
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.sql.DataSource

@Dependent
class RepositoriesConfiguration {

    @Inject
    @DefaultBean
    fun reviewRepository(dataSource: DataSource): ReviewRepository {
        return ReviewRepositoryJdbc(dataSource)
    }

    @Inject
    @DefaultBean
    fun reviewSummaryRepository(dataSource: DataSource): ReviewSummaryRepository {
        return ReviewSummaryRepositoryJdbc(dataSource)
    }

}