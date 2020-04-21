package simulations

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.utils.RestaurantIdGenerator

import scala.concurrent.duration.DurationInt
import scala.util.Random

class CreateNewReviewsSimulation extends GenericSimulation {

  private val rnd = new Random()
  private val restaurantIdGenerator = new RestaurantIdGenerator()

  private def getRandomUUID: UUID = {
    UUID.randomUUID()
  }

  private def getRandomRestaurantId: UUID = {
    restaurantIdGenerator.getRandomRestaurantId()
  }

  val customFeeder: Iterator[Map[String, Any]] = Iterator.continually(Map(
    "restaurantId" -> getRandomRestaurantId,
    "orderId" -> getRandomUUID,
    "userId" -> getRandomUUID,
    "review" -> rnd.nextInt(5)
  ))

  private def createNewReview() = {
    feed(customFeeder).
      exec(http("Create new Review")
        .post("review")
        .body(ElFileBody("bodies/NewReviewTemplate.json")).asJson
        .check(status.is(201)))
  }

  private val scn = scenario("Creating reviews")
    .forever() {
      exec(createNewReview())
    }

  setUp(
    scn.inject(
      rampUsers(userCount) during (rampDuration seconds)
    ).protocols(httpConf.inferHtmlResources())
  ).maxDuration(testDuration seconds)

}
