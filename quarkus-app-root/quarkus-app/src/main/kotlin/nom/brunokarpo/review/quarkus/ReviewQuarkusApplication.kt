package nom.brunokarpo.review.quarkus

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
class ReviewQuarkusApplication: QuarkusApplication {
    override fun run(vararg args: String?): Int {
        Quarkus.waitForExit()
        return 0
    }
}

fun main(vararg args: String) {
    Quarkus.run(ReviewQuarkusApplication::class.java, *args)
}