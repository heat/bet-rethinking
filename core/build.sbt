name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.immutables" % "value" % "2.2.12",
  "org.immutables" % "builder" % "2.2.12",
  "black.door" % "hate" % "v1r4t0",
  "org.mockito" % "mockito-core" % "1.10.19" % "test"
)
