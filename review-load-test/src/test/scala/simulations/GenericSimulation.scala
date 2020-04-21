package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

abstract class GenericSimulation extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/review/")
    .header("Accept", "application/json")

}
