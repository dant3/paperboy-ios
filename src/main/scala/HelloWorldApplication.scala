import org.robovm.apple.uikit._

class HelloWorldApplication extends UIApplicationDelegateAdapter {
  var window: UIWindow = _

  override def didFinishLaunching(application:UIApplication, launchOptions:UIApplicationLaunchOptions): Boolean = {
    val myViewController = new MyViewController()

    window = new UIWindow(UIScreen.getMainScreen.getBounds)
    window.setRootViewController(myViewController)
    window.makeKeyAndVisible()
    addStrongRef(window)

    true
  }
}