package simulations

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import simulations.utils.RestaurantIdGenerator


class GetSummaryReviewByRestaurantId {

  private val restaurantIdGenerator = new RestaurantIdGenerator()

  private def getRandomRestaurantId: UUID = {
    restaurantIdGenerator.getRandomRestaurantId()
  }

  def getRestaurantSummaryReview: ChainBuilder = {
    exec(http("Find summary review by restaurant id")
      .get("review").queryParam("restaurant_id", getRandomRestaurantId)
      .check(status.is(200)))
  }


}
