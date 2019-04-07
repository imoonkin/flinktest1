package testPackage1

import org.apache.flink.streaming.api.scala._
object testS1 {

  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val socketStream = env.socketTextStream("localhost",9000)
    val flatStream = socketStream.flatMap(value => value.split("\\s+"))

    val w1=flatStream.map(value => (value, 1)).startNewChain().keyBy(0).sum(1)
    val w2=flatStream.map(value => (value+"ABC", 1)).startNewChain().keyBy(0).sum(1)
    val w3=flatStream.map(value => (value.charAt(0).toString, 1)).startNewChain().keyBy(0).sum(1)
    val A1=w1.union(w2).keyBy(0).sum(1)
    val A2=A1.union(w3).keyBy(0).sum(1)

    val testStream=new testbranch(flatStream).flow

    testStream.print()

    env.execute()

  }
}