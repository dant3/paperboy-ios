package com.dant3.paperboy.core.db

import slick.driver.JdbcProfile

trait DriverDefinition {
  protected val driver: JdbcProfile
  protected final def driverName[T <: java.sql.Driver : Manifest]:String = implicitly[Manifest[T]].runtimeClass.getName
}