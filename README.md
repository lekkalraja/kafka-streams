# kafka-streams
## Stream Processing Steps :
* Identifying Events
* Modelling Events : Data/Process Modelling (Json, Avro, XML, etc...)
* Transporting Events : Continuosly transmit/transport events and turn them into a stream
* Processing Events

## Design Considerations
    1. Time Sensitivity
    2. Decoupling
    3. Data format Evolution
    4. Reliability
    5. Scalability
## Evaluating Alternatives
    1. Shared Database (DB's are good for Data at rest instead of Data in motion)
    2. RPC/RMI
    3. File Transfer
    4. Messaging

* Decision Criteria
* Alternative Options

## Kafka
* Kafka is a Message Broker Responsible for
    * Receive messages from the producers and acknowledge the successful receipt
    * Store the messages in a log file to safeguard it from potential loss
    * Deliver the messages to the consumers when they request it.

* Apache Kafka is a Horizontally scalable, fault-tolerant, distributed streaming platform.

### kafka Storage Architecture
                                                                    -> Replicas -> Segments (LogFile -> Actual messages resides)
*  Topics(Logical Name) -->  Partitions (Physical Directory)  ->    -> Replicas -> Segments (LogFile -> Actual messages resides)
                                                                    -> Replicas -> Segments (LogFile -> Actual messages resides)

* Segment file will be created by kafka if max size of log file exceeds it will stop writing to current segment log file and creates new segment file with extension `.log` .Every segment file name start's with the first offset index of that particular segment file.

### Setting up Kafka in Local 
* Download Confluent Kafka [Confluent](https://www.confluent.io/download/)
```shellscript
export CONFLUENT_HOME=<path-to-confluent>

export PATH=$PATH:$CONFLUENT_HOME/bin

confluent local services start

confluent local services stop -> To stop all the services
```

### Kafka Cluster Architecture
* Group of Kafka Brokers called as kafka Cluster, which is maintained by zookeeper(group of zNodes) and have one controller in kafka cluster to do administrative tasks.

### Kafka Work Distribution Architecture
#### Partition Assignment 

* Assume we have 6 node cluster with 2 Racks (R1, R2) each contain 3 brokers.
* Topic : Streams , Partitions : 10 , Replication Factor : 3
* Total Replicas : 30 will distribute based on
    * Even Distribution
    * Fault Tolerance

