import com.typesafe.sbt.packager.docker.ExecCmd
enablePlugins(JavaAppPackaging, AshScriptPlugin)

dockerBaseImage := "openjdk:8-jre-alpine"
packageName in Docker := "week9-10"

name := "week9-10"

version := "0.2"

scalaVersion := "2.13.5"

scalaVersion := "2.12.6"

val akkaVersion = "2.6.8"
val akkaHttpVersion = "10.2.4"
val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "de.heikoseeberger" %% "akka-http-circe" % "1.21.0",

  "org.scalatest" %% "scalatest" % "3.0.5" % Test,

  "ch.qos.logback" % "logback-classic" % "1.2.3",
)

dockerCommands := dockerCommands.value.map {
  case ExecCmd("CMD", _ @ _*) =>
    ExecCmd("CMD", "/opt/docker/bin/week9-10")
  case other =>
    other
}

//535a9b25d239  01e79041-750f-4e0c-974f-6a9930929f9a
