package nom.brunokarpo.review.quarkus.configurations.gateways

import io.quarkus.arc.DefaultBean
import nom.brunokarpo.review.core.repository.ReviewRepository
import nom.brunokarpo.review.core.repository.ReviewSummaryRepository
import nom.brunokarpo.review.quarkus.repositories.ReviewRepositoryJdbc
import nom.brunokarpo.review.quarkus.repositories.ReviewSummaryRepositoryJdbc
import javax.enterprise.context.Dependent
import javax.sql.DataSource
import javax.ws.rs.Produces

@Dependent
class RepositoriesConfiguration {

    @Produces
    @DefaultBean
    fun reviewRepository(dataSource: DataSource): ReviewRepository {
        return ReviewRepositoryJdbc(dataSource)
    }

    @Produces
    @DefaultBean
    fun reviewSummaryRepository(dataSource: DataSource): ReviewSummaryRepository {
        return ReviewSummaryRepositoryJdbc(dataSource)
    }

}