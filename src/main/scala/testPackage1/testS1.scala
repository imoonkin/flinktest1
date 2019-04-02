package testPackage1

import org.apache.flink.streaming.api.scala._

class testS1 {
  val env = StreamExecutionEnvironment.getExecutionEnvironment
  val socketStream = env.socketTextStream("localhost",9000)
  val wordsStream = socketStream.flatMap(value => value.split("\\s+")).map(value => (value,1))

  // this is another branch
  val wordsStreamABC = wordsStream.map(value => (value._1+"ABC", value._2))
  val combinedStream = wordsStream.union(wordsStreamABC)

  val keyValuePair = combinedStream.keyBy(0)
  val countPair = keyValuePair.sum(1)

  countPair.print()
  env.execute()

}
