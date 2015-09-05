package com.dant3.paperboy.core.rss

import java.util.Date

trait RssFeed {
  val link:String
  val title:String
  val desc:String
  val items:Seq[RssItem]
  override def toString = s"$title: $desc"

  def latest = items.sortWith((a, b) => a.date.compareTo(b.date) > 0).headOption
}

case class AtomRssFeed(title:String, link:String, desc:String, items:Seq[RssItem]) extends RssFeed
case class XmlRssFeed(title:String, link:String, desc:String, language:String, items:Seq[RssItem]) extends RssFeed

case class RssItem(title:String, link:String, desc:String, date:Date, guid:String) {
  override def toString = s"$date $title"
}