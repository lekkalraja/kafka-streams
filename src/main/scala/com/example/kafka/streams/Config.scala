package com.example.kafka.streams

import java.util.Properties

import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.scala.Serdes

object Config {

  def getConfig: Properties = {
    val props = new Properties
    props.put(StreamsConfig.APPLICATION_ID_CONFIG,"word-count-stream-app")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "tricycle-01.srvs.cloudkafka.com:9094,tricycle-02.srvs.cloudkafka.com:9094,tricycle-03.srvs.cloudkafka.com:9094")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "10000")
    props.put("auto.offset.reset", "earliest")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("session.timeout.ms", "30000")
    props.put("security.protocol", "SASL_SSL")
    props.put("sasl.mechanism", "SCRAM-SHA-256")
    props.put("sasl.jaas.config", getJaasCFG)
    props
  }

  def getJaasCFG : String = {
    val username = "s78m90xp"
    val password = "JsAkTX90KYhpkceLUY32PxnLtfcjURIk"
    s"org.apache.kafka.common.security.scram.ScramLoginModule required username=$username password=$password;"
  }
}
