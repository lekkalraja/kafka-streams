package com.example.kafka.streams

import java.util.Properties

import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.scala.Serdes

object Config {

  def getConfig: Properties = {
    val props = new Properties
    props.put(StreamsConfig.APPLICATION_ID_CONFIG,"word-count-stream-app")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props
  }

}
