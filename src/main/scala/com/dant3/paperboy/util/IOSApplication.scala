package com.dant3.paperboy.util

import org.robovm.apple.foundation.{NSAutoreleasePool, NSObject}
import org.robovm.apple.uikit.{UIApplication, UIApplicationDelegate}

trait IOSApplication {
  def runIOSApplication[T <: NSObject with UIApplicationDelegate](args:Array[String])(implicit manifest: Manifest[T]):Unit = {
    withAutoreleasePool(UIApplication.main(args, null, manifest.runtimeClass.asInstanceOf[Class[T]]))
  }

  final def withAutoreleasePool(block: ⇒ Any):Any = {
    val pool = new NSAutoreleasePool()
    try {
      block
    } finally {
      pool.drain()
    }
  }
}
