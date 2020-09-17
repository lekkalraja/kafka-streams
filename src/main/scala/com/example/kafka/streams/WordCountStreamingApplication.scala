package com.example.kafka.streams

import java.lang

import org.apache.kafka.common.serialization.{Serde, Serdes}
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{Consumed, Grouped}

object WordCountStreamingApplication extends App {

  val inputTopic = "s78m90xp-word-count-input"
  val outputTopic = "s78m90xp-word-count-output"

  private val stringSerde: Serde[String] = Serdes.String()
  private val builder = new StreamsBuilder()
  implicit private val consumed: Consumed[String, String] = Consumed.`with`(stringSerde, stringSerde)
  builder.stream(inputTopic)
    .flatMapValues(line => line.toLowerCase.split(" "))
    .groupBy((_, word) => word)(Grouped.`with`(stringSerde, stringSerde))
    .count()(Materialized.as(outputTopic))


  private val streams = new KafkaStreams(builder.build(), Config.getConfig)
  streams.start()
  println(streams)

  Runtime.getRuntime.addShutdownHook(new Thread(() => streams.close()))
}
