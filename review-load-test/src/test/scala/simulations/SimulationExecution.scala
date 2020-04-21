package simulations

import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class SimulationExecution extends GenericSimulation {

  private val scn = scenario("Review Application Load Test")
      .forever() {
        exec(new GetSummaryReviewByRestaurantId().getRestaurantSummaryReview)
          .pause(2)
          .exec(new CreateNewReviewsSimulation().createNewReview)
      }

  setUp(
    scn.inject(
      rampUsers(userCount) during (rampDuration seconds)
    ).protocols(httpConf.inferHtmlResources())
  ).maxDuration(testDuration seconds)

}
