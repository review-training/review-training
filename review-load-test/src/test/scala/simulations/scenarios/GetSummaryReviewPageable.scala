package simulations.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.util.Random

class GetSummaryReviewPageable {

  private val rnd = new Random()

  def getReviewSummaryPage: ChainBuilder = {
    exec(http("Get review summary page")
    .get("review/list").queryParam("size", rnd.nextInt(20)).queryParam("page", rnd.nextInt(5))
    .check(status.is(200)))
  }

}
