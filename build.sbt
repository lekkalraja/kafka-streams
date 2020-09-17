name := "kafka-streams"

version := "0.1"

scalaVersion := "2.13.3"

val kafkaStreams = "2.6.0"
val logback = "1.1.3"
val jackson = "2.11.2"
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % kafkaStreams
libraryDependencies += "ch.qos.logback" % "logback-classic" % logback % Runtime
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jackson

