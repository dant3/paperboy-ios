package com.dant3.paperboy.ui

import java.net.URL

import com.dant3.paperboy.rss.{RssFeed, RssItem, RssReader}
import com.dant3.paperboy.util.{MainThread, NSLog, RxSeqDataSource}
import org.robovm.apple.uikit.{UIRectEdge, UITableViewCell, UIViewController}
import org.robovm.rt.bro.Bits
import rx.lang.scala.JavaConversions._
import rx.schedulers.Schedulers

import scala.concurrent.Future

class RssFeedViewController extends UIViewController with RssFeedUi {
  import MainThread.context

  lazy val feedDataSource = new RxSeqDataSource[RssItem, UITableViewCell](RssFeedCell)
  private lazy val javaFeedUrl = new URL("http://habrahabr.ru/rss/hub/java/")

  override def viewDidLoad() = {
    setEdgesForExtendedLayout(Bits.`with`(UIRectEdge.Left, UIRectEdge.Bottom, UIRectEdge.Right))
    getNavigationItem.setTitle("Paperboy")

    listView.setDataSource(feedDataSource)
    feedDataSource.observe().
               observeOn(Schedulers.from(MainThread)).
               subscribe(_ ⇒ listView.reloadData())

    getView.addSubview(listView)

    loadFeed() onSuccess {
      case feed ⇒
        getNavigationItem.setTitle(s"Paperboy: ${feed.title}")
        feedDataSource.update(feed.items)
    }
  }
  
  private def loadFeed(): Future[RssFeed] = {
    NSLog(s"Loading feed at $javaFeedUrl")
    listView.setProgressVisible(true)

    val feedLoading = RssReader.load(javaFeedUrl)
    feedLoading onComplete( res ⇒ {
      NSLog(s"Loading finished! Result: $res")
      listView.setProgressVisible(false)
    })
    feedLoading
  }
}
