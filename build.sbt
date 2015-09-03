import sbtrobovm.RobovmPlugin._

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  // --- robovm ---
  "org.robovm" % "robovm-rt" % RoboVMVersion
  , "org.robovm" % "robovm-cocoatouch" % RoboVMVersion
  // --- rx ---
  , "io.reactivex" %% "rxscala" % "0.24.0"
)