package testPackage1

import org.apache.flink.streaming.api.scala._
class testbranch(var flatStream:DataStream[String]) {
  def flow: DataStream[(String, Int)] ={
    val wordsStream = flatStream.map(value => (value, 1)).startNewChain()

    // this is another branch
    val wordsStreamABC = flatStream.map(value => (value+"ABC", 2)).startNewChain()
    val combinedStream = wordsStream.union(wordsStreamABC)

    val keyValuePair = combinedStream.keyBy(0)
    val countPair = keyValuePair.sum(1)
    countPair.print()

    val P = wordsStream.keyBy(0)
    val cP = P.sum(1)
    cP
  }

}
