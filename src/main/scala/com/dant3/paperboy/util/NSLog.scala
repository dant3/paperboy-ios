package com.dant3.paperboy.util

import org.robovm.apple.foundation.Foundation

object NSLog {
  def apply(message: String) = Foundation.log(message)
  
  def symbols(failure: Throwable): String = {
    val description = failure.getClass.getName + ": " + failure.getMessage
    def stacktrace = failure.getStackTrace.view.map(toString)

    Seq(description) ++ stacktrace mkString "\n"
  }

  private def toString(stackFrame: StackTraceElement): String = {
    s"    ${stackFrame.getClassName}:${stackFrame.getMethodName}(${stackFrame.getFileName}:${stackFrame.getLineNumber})"
  }
}
