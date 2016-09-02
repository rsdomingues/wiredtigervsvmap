package com.ciandt.poc.mongodb

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Cart {

  val addItem = exec(
    io.gatling.http.Predef.http("addCart")
      .post("/sync/carts")
      .body(StringBody("""{"id":"myCart","campaign":"Camp","status":"OPENED"}"""))
      .asJSON
      .check(
        status.is(201)
      )
  )

  val addItemAsync = exec(
    io.gatling.http.Predef.http("addCartAsync")
      .post("/async/carts")
      .body(StringBody("""{"id":"myCart","campaign":"Camp","status":"OPENED"}"""))
      .asJSON
      .check(
        status.is(201)
      )
  )

  val findAll = exec(
    io.gatling.http.Predef.http("readCart")
      .get("/async/carts")
      .check(
        status.is(200)
      )
  )

  val findAllAsync = exec(
    io.gatling.http.Predef.http("readCartAsync")
      .get("/async/carts")
      .check(
        status.is(200)
      )
  )

  val findOne = exec(
    io.gatling.http.Predef.http("readCart")
      .get("/async/carts/notConcurrent")
      .check(
        status.is(200)
      )
  )

  val findOneAsync = exec(
    io.gatling.http.Predef.http("readCartAsync")
      .get("/async/carts/notConcurrent")
      .check(
        status.is(200)
      )
  )
}
