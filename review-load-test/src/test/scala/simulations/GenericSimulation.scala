package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

abstract class GenericSimulation extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/review/")
    .header("Accept", "application/json")

  def userCount: Int = getProperty("USERS", "60").toInt
  def rampDuration: Int = getProperty("RAMP_DURATION", "60").toInt
  def testDuration: Int = getProperty("DURATION", "180").toInt

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

}
