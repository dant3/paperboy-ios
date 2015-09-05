package com.dant3.paperboy.ui

import com.dant3.paperboy.core.rss.RssItem
import com.dant3.paperboy.util.CellBinder
import org.robovm.apple.uikit.UITableViewCell


object RssFeedCell extends CellBinder[RssItem, UITableViewCell] {
  override def create: UITableViewCell = new UITableViewCell
  override def bind(data: RssItem, view: UITableViewCell): UITableViewCell = {
    for (label ← Option(view.getTextLabel)) label.setText(data.title)
    for (details ← Option(view.getDetailTextLabel)) details.setText(data.desc)
    view
  }
}
