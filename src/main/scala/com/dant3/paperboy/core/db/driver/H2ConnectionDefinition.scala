package com.dant3.paperboy.core.db.driver

import java.io.File

import com.dant3.paperboy.core.db.ConnectionDefinition
import slick.driver.H2Driver
import slick.jdbc.JdbcBackend
import slick.jdbc.JdbcBackend.DatabaseDef

trait H2ConnectionDefinition extends ConnectionDefinition {
  override protected lazy val driver = H2Driver

  protected def createConnection(dbFile: File): DatabaseDef = {
    dbFile.mkdirs()
    createConnection(dbFile.getAbsolutePath)
  }

  protected def createConnection(where: String): DatabaseDef = {
    org.h2.Driver.load()
    JdbcBackend.Database.forURL(
      h2ConnectionPrefix + where,
      driver = driverName[org.h2.Driver]
    )
  }
  protected val h2ConnectionPrefix = "jdbc:h2:"
}