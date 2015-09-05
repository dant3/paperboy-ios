package com.dant3.paperboy.core.db.tables

import com.dant3.paperboy.core.db.DriverDefinition

trait Tables extends FeedsTable with DatabaseMetaTable { this: DriverDefinition ⇒
  import driver.api._

  protected def schema = feeds.schema ++ metaInfo.schema
}
