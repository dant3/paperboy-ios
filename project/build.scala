import sbt.Keys._
import sbt._
import sbtrobovm.RobovmPlugin._

object MyBuild extends Build {
    lazy val root = iOSProject(id = "paperboy", base = file("."), settings = Seq(
      robovmConfiguration := Left(file("robovm.xml")),
      robovmTarget64bit := false,
      javacOptions ++= Seq(
        "-Xlint",
        "-encoding", "UTF-8"
      ), scalacOptions ++= Seq(
        "-Xlint",
        "-Ywarn-dead-code",
        "-Ywarn-value-discard",
        "-Ywarn-numeric-widen",
        "-Ywarn-unused",
        "-Ywarn-unused-import",
        "-unchecked",
        "-deprecation",
        "-feature",
        "-encoding", "UTF-8"
      )
    ))
}
