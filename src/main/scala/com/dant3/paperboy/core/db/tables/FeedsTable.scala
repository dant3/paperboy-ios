package com.dant3.paperboy.core.db.tables

import com.dant3.paperboy.core.db.DriverDefinition

trait FeedsTable { this:DriverDefinition ⇒
  import driver.api._
  
  case class Feed(link: String, title: String, desc: String)

  class Feeds(tag:Tag) extends Table[Feed](tag, "feeds") {
    def link = column[String]("link", O.PrimaryKey)
    def title = column[String]("title")
    def desc = column[String]("desc")
    //def registrationDate = column[DateTime]("registrationDate")
    def * = (link, title, desc) <> (Feed.tupled, Feed.unapply)
    def titleIndex =  index("idx_title", title)
  }

  val feeds = TableQuery[Feeds]

  def findFeedByLink(link: String) = feeds.filter(_.link === link).take(1).result.headOption
}
