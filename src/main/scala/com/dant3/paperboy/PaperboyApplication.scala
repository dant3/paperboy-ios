package com.dant3.paperboy

import java.io.File

import com.dant3.paperboy.core.db.Database
import com.dant3.paperboy.core.db.driver.H2DatabaseFile
import com.dant3.paperboy.core.db.tables.SchemeManager
import com.dant3.paperboy.ui.RssFeedViewController
import com.dant3.paperboy.util.NSLog
import org.robovm.apple.uikit._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class PaperboyApplication extends UIApplicationDelegateAdapter {
  def window = Option(getWindow)
  def window_= (window: UIWindow): Unit = {
    this.window.foreach(removeStrongRef)
    setWindow(window)
    this.window.foreach(addStrongRef)
  }

  lazy val db = new Database with SchemeManager with H2DatabaseFile {
    override def databaseFile: File = new File(Cache.cacheRootDirectory.get, "cache")
  }

  override def didFinishLaunching(application:UIApplication, launchOptions:UIApplicationLaunchOptions): Boolean = {
    application.setStatusBarStyle(UIStatusBarStyle.Default, true)

    Await.result(db.createSchema, 10 seconds)

    val rssFeedController = new RssFeedViewController()
    val navigationController = new UINavigationController(rssFeedController)

    val window = new UIWindow(UIScreen.getMainScreen.getBounds)
    window.setRootViewController(navigationController)
    window.makeKeyAndVisible()
    this.window = window

    NSLog(s"Using cache directory: ${Cache.cacheRootDirectory}")

    true
  }
}