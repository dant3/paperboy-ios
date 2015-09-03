import org.robovm.apple.foundation.NSSet
import org.robovm.apple.uikit._
import rx.lang.scala.RxVar

import scala.annotation.tailrec

class MyViewController extends UIViewController with MyViewControllerUi with UIImplicits {
  private val name = RxVar("World")

  override def viewDidLoad() {
    getView.addSubview(background)
    getView.addSubview(textField)
    getView.addSubview(label)

    textField.addOnEditingChangedListener(name := textField.getText)

    name.map(greeting).subscribe(label.setText _)
  }

  private def factorial(n:Long):Long = {
    @tailrec def factorialImpl(n:Long, accumulator:Long):Long = {
      if (n <= 1) accumulator
      else factorialImpl(n - 1, accumulator * n)
    }
    factorialImpl(n, 1)
  }

  override def touchesBegan(touches:NSSet[UITouch], event:UIEvent) {
    textField.resignFirstResponder()
    textField.setText(name())
    super.touchesBegan(touches, event)
  }

  def greeting(name:String) = s"Hello, $name!"
}
