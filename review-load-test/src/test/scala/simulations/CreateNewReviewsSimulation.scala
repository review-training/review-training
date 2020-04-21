package simulations

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.utils.{RestaurantIdGenerator}

import scala.concurrent.duration.DurationInt
import scala.util.Random

class CreateNewReviewsSimulation extends GenericSimulation {

  private var rnd = new Random()
  private val restaurantIdGenerator = new RestaurantIdGenerator()

  private def getRandomUUID(): UUID = {
    UUID.randomUUID()
  }

  private def getRandomRestaurantId(): UUID = {
    restaurantIdGenerator.getRandomRestaurantId()
  }

  val customFeeder = Iterator.continually(Map(
    "restaurantId" -> getRandomRestaurantId(),
    "orderId" -> getRandomUUID(),
    "userId" -> getRandomUUID(),
    "review" -> rnd.nextInt(5)
  ))

  def createNewReview() = {
    feed(customFeeder).
      exec(http("Create new Review")
        .post("review")
        .body(ElFileBody("bodies/NewReviewTemplate.json")).asJson
        .check(status.is(201)))
  }

  val scn = scenario("Creating reviews")
    .forever() {
      exec(createNewReview())
        .pause(500.milliseconds)
    }

  setUp(
    scn.inject(
      atOnceUsers(1)
    ).protocols(httpConf.inferHtmlResources())
  ).maxDuration(10 seconds)

}
