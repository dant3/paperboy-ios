package com.dant3.paperboy.util

import org.robovm.apple.dispatch.DispatchQueue
import org.robovm.apple.foundation.NSThread

object MainThread extends GCDContextExecutor(DispatchQueue.getMainQueue) {
  override def execute(command: Runnable): Unit = if (NSThread.isCurrentThreadMainThread) {
    command.run()
  } else {
    super.execute(command)
  }
}