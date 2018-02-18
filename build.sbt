name := "worldpay-merchant-offer"
version := "1.0-SNAPSHOT"
scalaVersion := "2.12.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  ws,
  "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided",
  "ch.qos.logback" % "logback-classic" % "1.2.3",

  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % "test",
  "org.mockito" % "mockito-core" % "2.15.0" % "test"
)