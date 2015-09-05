package com.dant3.paperboy.core.db.tables

import com.dant3.paperboy.core.db.Database

trait SchemeManager { self: Database ⇒
  import driver.api._

  def createSchema = run(DBIO.seq(
    schema.create,
    metaInfo += schemaVersionMeta(1)
  ))
  def dropSchema = run(schema.drop)

  private def schemaVersionMeta(version: Int) = MetaInfo("schema_version", version.toString)
}
