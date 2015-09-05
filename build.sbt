import sbtrobovm.RobovmPlugin._

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  // --- robovm ---
  "org.robovm" % "robovm-rt" % RoboVMVersion
  , "org.robovm" % "robovm-cocoatouch" % RoboVMVersion
  // --- rx ---
  , "io.reactivex" %% "rxscala" % "0.24.0"
  // --- xml ---
  , "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
  // --- DB access ---
  ,"com.typesafe.slick" %% "slick" % "3.0.1"
  ,"com.h2database" % "h2" % "1.3.170"
)
