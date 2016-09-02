package com.ciandt.poc

import com.ciandt.poc.mongodb.Cart
import io.gatling.core.Predef._

import scala.concurrent.duration._
/**
 * Setup: http://gatling.io/docs/2.1.7/general/simulation_setup.html
 * Cheat sheet: http://gatling.io/#/cheat-sheet/2.1.7
 * Advanced: http://gatling.io/docs/2.1.7/advanced_tutorial.html
 * Parameters: http://gatling.io/docs/2.1.7/cookbook/passing_parameters.html
 * Scaling: http://gatling.io/docs/2.1.7/cookbook/scaling_out.html
 * Configuration: http://gatling.io/docs/2.1.7/general/configuration.html
 *   Config examples: https://github.com/typesafehub/config
 *   https://typesafehub.github.io/config/latest/api/com/typesafe/config/ConfigFactory.html
 *   http://danielasfregola.com/2015/06/01/loading-configurations-in-scala/
 *   http://blog.ometer.com/2011/12/09/configuring-the-typesafe-stack/
 * 
 * Run with Variables: mvn gatling:execute -DfileLocation=/my/conf/location/myconf.conf
 * 
 * Scala Methods: http://joelabrahamsson.com/learning-scala-part-five-methods/
 */
class SyncSimulation extends Simulation {
  val cartSimularionScenario = scenario("Cart Simulation").exec(Cart.addItemAsync, Cart.findOneAsync)

  setUp(
      cartSimularionScenario.inject(
          atOnceUsers(10),
          rampUsersPerSec(10) to 50 during(60 seconds),
          constantUsersPerSec(50) during(30 seconds)
      )
  ).protocols(Protocol.HTTP)
}