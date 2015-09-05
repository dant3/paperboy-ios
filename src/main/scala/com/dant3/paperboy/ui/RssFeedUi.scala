package com.dant3.paperboy.ui

import com.dant3.paperboy.util.UIImplicits
import org.robovm.apple.uikit._

trait RssFeedUi extends UIImplicits { self :UIViewController ⇒
  def uiFrame = self.getView.getBounds

  lazy val listView = new UITableView(uiFrame, UITableViewStyle.Plain) {
    def setProgressVisible(visible: Boolean): Unit = if (visible) {
      addSubview(loadingIndicator)
    } else {
      loadingIndicator.removeFromSuperview()
    }
  }

  lazy val loadingIndicator: UIActivityIndicatorView = new UIActivityIndicatorView(UIActivityIndicatorViewStyle.Gray) {
    setFrame(listView.getFrame.withSize(50, 50).centerIn(listView))
  }
}
