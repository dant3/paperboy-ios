package com.dant3.paperboy.util

import org.robovm.apple.coregraphics.{CGSize, CGPoint, CGRect}
import org.robovm.apple.uikit.UIView

import scala.language.implicitConversions

trait GeometryImplicits {
  implicit def toCGRect(rectBounds:(Double,Double,Double,Double)):CGRect = rectBounds match {
    case (x:Double, y:Double, w:Double, h:Double) ⇒ new CGRect(x, y, w, h)
  }

  implicit def toCGPoint(point: (Double, Double)): CGPoint = new CGPoint(point._1, point._2)

  implicit class FluentRect(source: CGRect) {
    def withInsets(left: Double = 0, right: Double = 0, top: Double = 0, bottom: Double = 0) = {
      new CGRect(source.getOrigin.getX + left, source.getOrigin.getY + top,
        source.getWidth - left - right, source.getHeight - top - bottom)
    }

    def withWidth(width: Double) = {
      withSize(new CGSize(width, source.getHeight))
    }

    def withHeight(height: Double) = {
      withSize(new CGSize(source.getWidth, height))
    }

    def reduceBy(width: Double = 0, height: Double = 0) = withSize(new CGSize(width, height))

    def withOrigin(origin: CGPoint) = {
      new CGRect(origin, source.getSize)
    }

    def centerIn(view: UIView): CGRect = centerIn(view.getFrame)

    def centerIn(otherRect: CGRect): CGRect = new CGRect(
      source.center.moveBy(-(otherRect.getWidth / 2), -(otherRect.getHeight / 2)),
      otherRect.getSize
    )

    def center:CGPoint = source.getOrigin.moveBy(source.getWidth / 2, source.getHeight / 2)

    def withSize(width: Double, height: Double) = {
      new CGRect(source.getOrigin, new CGSize(width, height))
    }

    def withSize(size: CGSize) = {
      new CGRect(source.getOrigin, size)
    }
  }


  implicit class FluentPoint(origin: CGPoint) {
    def moveBy(x: Double, y: Double) = new CGPoint(origin.getX + x, origin.getY + y)
  }
}
