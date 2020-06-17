package nom.brunokarpo.review.micronaut

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("nom.brunokarpo.review.micronaut")
                .mainClass(Application.javaClass)
                .start()
    }
}