package testPackage1

import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala._
import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object testS1 {

  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.enableCheckpointing(7000, CheckpointingMode.EXACTLY_ONCE)
    val properties = new Properties();
    properties.setProperty("bootstrap.servers", "localhost:9092");
    properties.setProperty("group.id", "test");

    val socketStream = env.addSource(
      new FlinkKafkaConsumer[String](
        "topiczhuheyi", new SimpleStringSchema(), properties))
    //val socketStream = env.socketTextStream("localhost",9000)
    val flatStream = socketStream.flatMap(value => value.split("\\s+"))

    val w1=flatStream.map(value => (value, 1)).startNewChain().keyBy(0).sum(1)
    val w2=flatStream.map(value => (value+"ABC", 1)).startNewChain().keyBy(0).sum(1)
    val w3=flatStream.map(fun = value => {
      Thread.sleep(5000)
      (value.charAt(0).toString, 2)
    }).startNewChain().keyBy(0).sum(1)
    val A1=w1.union(w2).keyBy(0).sum(1)
    val A2=A1.union(w3).keyBy(0).sum(1)

    //val testStream=new testbranch(flatStream).flow  a

    A2.print()

    env.execute()

  }
}
