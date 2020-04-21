package simulations

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.utils.RestaurantIdGenerator

import scala.concurrent.duration.DurationInt

class GetSummaryReviewByRestaurantId extends GenericSimulation {

  private val restaurantIdGenerator = new RestaurantIdGenerator()

  private def getRandomRestaurantId: UUID = {
    restaurantIdGenerator.getRandomRestaurantId()
  }

  private def getRestaurantSummaryReview = {
    exec(http("Find summary review by restaurant id")
      .get("review").queryParam("restaurant_id", getRandomRestaurantId)
      .check(status.is(200)))
  }

  private val scn = scenario("Retrieving restaurant summary")
    .forever() {
      exec(getRestaurantSummaryReview)
    }

  setUp(
    scn.inject(
      rampUsers(userCount) during (rampDuration seconds)
    ).protocols(httpConf.inferHtmlResources())
  ).maxDuration(testDuration seconds)
}
