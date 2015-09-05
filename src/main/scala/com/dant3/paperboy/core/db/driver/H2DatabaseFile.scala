package com.dant3.paperboy.core.db.driver

import java.io.File

trait H2DatabaseFile extends H2ConnectionDefinition {
  override protected lazy val databaseConnection = createConnection(databaseFile)
  def databaseFile: File
}
