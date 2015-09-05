package com.dant3.paperboy

import com.dant3.paperboy.ui.RssFeedViewController
import org.robovm.apple.uikit._

class PaperboyApplication extends UIApplicationDelegateAdapter {
  private var _window: Option[UIWindow] = None

  def window = this._window
  def window_= (window: UIWindow): Unit = {
    this._window = Option(window)
    this._window.foreach(addStrongRef)
  }

  override def didFinishLaunching(application:UIApplication, launchOptions:UIApplicationLaunchOptions): Boolean = {
    application.setStatusBarStyle(UIStatusBarStyle.Default, true)

    val rssFeedController = new RssFeedViewController()
    val navigationController = new UINavigationController(rssFeedController)

    val window = new UIWindow(UIScreen.getMainScreen.getBounds)
    window.setRootViewController(navigationController)
    window.makeKeyAndVisible()
    this.window = window

    true
  }
}