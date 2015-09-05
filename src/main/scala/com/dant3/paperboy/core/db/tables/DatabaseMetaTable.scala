package com.dant3.paperboy.core.db.tables

import com.dant3.paperboy.core.db.DriverDefinition

trait DatabaseMetaTable { this:DriverDefinition ⇒
  import driver.api._

  case class MetaInfo(key: String, value: String)

  class Feeds(tag:Tag) extends Table[MetaInfo](tag, "__meta_info__") {
    def key = column[String]("key", O.PrimaryKey)
    def value = column[String]("value")
    def * = (key, value) <> (MetaInfo.tupled, MetaInfo.unapply)
  }

  val metaInfo = TableQuery[Feeds]
}
