package nom.brunokarpo.review.app

import nom.brunokarpo.review.repository.spring.jdbc.JdbcDatabaseConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
        scanBasePackageClasses = [ReviewApplication::class,
            JdbcDatabaseConfiguration::class]
)
class ReviewApplication

fun main(args: Array<String>) {
    runApplication<ReviewApplication>(*args)
}