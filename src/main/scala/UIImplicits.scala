import org.robovm.apple.coregraphics.CGRect
import org.robovm.apple.uikit.UIControl
import org.robovm.apple.uikit.UIControl.OnEditingChangedListener

import scala.language.implicitConversions

trait UIImplicits {
  implicit def onEditingChangedListener(fn:UIControl ⇒ Any): OnEditingChangedListener = new OnEditingChangedListener {
    override def onEditingChanged(control: UIControl): Unit = fn(control)
  }

  implicit def onEditingChangedListener(block: ⇒ Any): OnEditingChangedListener = new OnEditingChangedListener {
    override def onEditingChanged(control: UIControl): Unit = block
  }

  implicit def toRect(rectBounds:(Int,Int,Int,Int)):CGRect = rectBounds match {
    case (x:Int, y:Int, w:Int, h:Int) ⇒ new CGRect(x, y, w, h)
  }
}
