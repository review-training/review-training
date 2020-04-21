package simulations.scenarios

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import simulations.utils.RestaurantIdGenerator

import scala.util.Random

class CreateNewReviewsSimulation {

  private val rnd = new Random()
  private val restaurantIdGenerator = new RestaurantIdGenerator()

  private def getRandomUUID: UUID = {
    UUID.randomUUID()
  }

  private def getRandomRestaurantId: UUID = {
    restaurantIdGenerator.getRandomRestaurantId()
  }

  private def getReview: Int = {
    rnd.nextInt(5) + 1
  }

  private val customFeeder: Iterator[Map[String, Any]] = Iterator.continually(Map(
    "restaurantId" -> getRandomRestaurantId,
    "orderId" -> getRandomUUID,
    "userId" -> getRandomUUID,
    "review" -> getReview
  ))

  def createNewReview: ChainBuilder = {
    feed(customFeeder).
      exec(http("Create new Review")
        .post("review")
        .body(ElFileBody("bodies/NewReviewTemplate.json")).asJson
        .check(status.is(201)))
  }
}
