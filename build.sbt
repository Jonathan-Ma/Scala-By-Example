name := "ScalaByExample"

organization := "org.ma"

version := "1.0"

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)

resolvers += Classpaths.sbtPluginReleases

publishArtifact in Test := false

parallelExecution in Test := false
