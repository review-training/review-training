package simulations

import io.gatling.core.Predef._
import simulations.scenarios.{CreateNewReviewsSimulation, GetSummaryReviewByRestaurantId, GetSummaryReviewPageable}

import scala.concurrent.duration.DurationInt

class SimulationExecution extends GenericSimulation {

  private val scn = scenario("Review Application Load Test")
      .forever() {
        exec(new GetSummaryReviewByRestaurantId().getRestaurantSummaryReview)
          .exec(new CreateNewReviewsSimulation().createNewReview)
          .exec(new GetSummaryReviewPageable().getReviewSummaryPage)
      }

  setUp(
    scn.inject(
      rampUsers(userCount) during (rampDuration.second)
    ).protocols(httpConf.inferHtmlResources())
  ).maxDuration(testDuration.second)

}
