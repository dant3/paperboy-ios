package com.dant3.paperboy.core.db.driver

import com.dant3.paperboy.core.db.Database

trait TempH2Connection extends H2ConnectionDefinition { self:Database ⇒
  override protected lazy val databaseConnection = createConnection(s"mem:test1;${h2options(keepData = true)}")
  private def h2options(keepData:Boolean) = if (keepData) "DB_CLOSE_DELAY=-1" else ""
}
