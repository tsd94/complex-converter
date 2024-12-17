ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "complex-converter"
  )

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

