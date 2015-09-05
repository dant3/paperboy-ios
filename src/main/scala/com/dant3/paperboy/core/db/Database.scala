package com.dant3.paperboy.core.db

import com.dant3.paperboy.core.db.tables.Tables
import slick.dbio.{DBIOAction, NoStream}

import scala.concurrent.Await
import scala.concurrent.duration._

trait Database extends Tables with ConnectionDefinition {
  lazy val api = driver.api


  final def run[R](action: DBIOAction[R, NoStream, Nothing]) = databaseConnection.run(action)
  final def runBlocking[R](action: DBIOAction[R, NoStream, Nothing])(implicit timeout:FiniteDuration = 30.seconds) =
  Await.result(databaseConnection.run(action), timeout)
}