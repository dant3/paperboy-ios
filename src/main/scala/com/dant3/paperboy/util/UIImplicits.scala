package com.dant3.paperboy.util

import org.robovm.apple.uikit.UIControl
import org.robovm.apple.uikit.UIControl.OnEditingChangedListener

import scala.language.implicitConversions

trait UIImplicits extends GeometryImplicits {
  implicit def onEditingChangedListener(fn:UIControl ⇒ Any): OnEditingChangedListener = new OnEditingChangedListener {
    override def onEditingChanged(control: UIControl): Unit = fn(control)
  }

  implicit def onEditingChangedListener(block: ⇒ Any): OnEditingChangedListener = new OnEditingChangedListener {
    override def onEditingChanged(control: UIControl): Unit = block
  }
}
