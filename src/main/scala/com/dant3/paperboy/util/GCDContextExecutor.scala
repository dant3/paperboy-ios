package com.dant3.paperboy.util

import org.robovm.apple.dispatch.DispatchQueue

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

class GCDContextExecutor(dispatchQueue: DispatchQueue) extends ExecutionContextExecutor {
  implicit lazy val context: ExecutionContext = this

  override def reportFailure(cause: Throwable): Unit =
    NSLog(s"${dispatchQueue.getLabel} failure: \n${NSLog.symbols(cause)}")

  override def execute(command: Runnable): Unit = dispatchQueue.async(command)
}

