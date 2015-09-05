package com.dant3.paperboy.rss

import java.net.URL
import java.text.SimpleDateFormat
import java.util.{Date, Locale}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.xml.{NodeSeq, Elem, XML}

object RssReader {
  def load(url: URL): Future[RssFeed] = Future {
    parseFeed(XML.load(url.openConnection.getInputStream))
  }


  private def parseFeed(xml: Elem) = {
    if((xml \\ "channel").length == 0) {
      parseAtomFeed(xml)
    } else {
      parseXmlFeed(xml)
    }
  }


  private def parseAtomFeed(xml: Elem) = {
    val dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH)

    def parseAtomDate(date: String, formatter: SimpleDateFormat): Date = {
      formatter.parse(date.reverse.replaceFirst(":", "").reverse)
    }

    def getHtmlLink(node: NodeSeq) = {
      node.filter(n => (n \ "@type").text == "text/html")
          .map( n => (n \ "@href").text).head
    }

    def extract(xml: Elem): Seq[RssFeed] = {
      for (feed <- xml \\ "feed") yield {
        val items = for (item <- feed \\ "entry") yield {
          RssItem(
            (item \\ "title").text,
            getHtmlLink(item \\ "link"),
            (item \\ "summary").text,
            parseAtomDate((item \\ "published").text, dateFormatter),
            (item \\ "id").text
          )
        }
        AtomRssFeed(
          (feed \ "title").text,
          getHtmlLink(feed \ "link"),
          (feed \ "subtitle ").text,
          items.sortBy(_.date)(Ordering[Date].reverse))
      }
    }

    extract(xml).head
  }

  private def parseXmlFeed(xml: Elem) = {
    val dateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)

    def extract(xml:Elem) : Seq[RssFeed] = {
      for (channel <- xml \\ "channel") yield {
        val items = for (item <- channel \\ "item") yield {
          RssItem(
            (item \\ "title").text,
            (item \\ "link").text,
            (item \\ "description").text,
            dateFormatter.parse((item \\ "pubDate").text),
            (item \\ "guid").text
          )
        }
        XmlRssFeed(
          (channel \ "title").text,
          (channel \ "link").text,
          (channel \ "description").text,
          (channel \ "language").text,
          items.sortBy(_.date)(Ordering[Date].reverse))
      }
    }

    extract(xml).head
  }
}
