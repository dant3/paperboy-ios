package com.dant3.paperboy.core.db

import slick.jdbc.JdbcBackend

trait ConnectionDefinition extends DriverDefinition {
  protected def databaseConnection: JdbcBackend.Database
}