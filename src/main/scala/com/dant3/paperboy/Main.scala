package com.dant3.paperboy

import com.dant3.paperboy.util.{NSLog, IOSApplication}

object Main extends IOSApplication {
  def main(args: Array[String]):Unit = try {
    runIOSApplication[PaperboyApplication](args)
  } catch {
    case failure: Throwable ⇒
      NSLog(s"Application crashed. Reason: ${NSLog.symbols(failure)}")
  }
}
