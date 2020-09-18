package com.example.kafka.streams

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.{Materialized, Produced}
import org.apache.kafka.streams.scala.{Serdes, StreamsBuilder}
import org.apache.kafka.streams.scala.kstream.{Consumed, Grouped}

object WordCountStreamingApplication extends App {

  val inputTopic = "word-count-input"
  val outputTopic = "word-count-output"

  private val stringSerde: Serde[String] = Serdes.String
  private val longSerde : Serde[Long] = Serdes.Long
  private val builder = new StreamsBuilder()
  implicit private val consumed: Consumed[String, String] = Consumed.`with`(stringSerde, stringSerde)

  builder.stream(inputTopic)
    .flatMapValues(line => line.toLowerCase.split(" "))
    .groupBy((_, word) => word)(Grouped.`with`(stringSerde, stringSerde))
    .count()(Materialized.`with`(stringSerde, longSerde))
    .toStream
    .to(outputTopic)(Produced.`with`(stringSerde, longSerde))


  private val streams = new KafkaStreams(builder.build(), Config.getConfig)
  streams.start()
  println(streams)

  Runtime.getRuntime.addShutdownHook(new Thread(() => streams.close()))
}
