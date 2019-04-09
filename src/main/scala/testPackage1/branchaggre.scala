package testPackage1

import org.apache.flink.streaming.api.scala._

class branchaggre(var flatStream:DataStream[String]){
  def flow: DataStream[(String, Int)]={
    val w1=flatStream.map(value => (value, 1)).startNewChain().keyBy(0).sum(1)
    val w2=flatStream.map(value => (value+"ABC", 1)).startNewChain().keyBy(0).sum(1)
    val w3=flatStream.map(value => (value.charAt(0).toString, 1)).startNewChain().keyBy(0).sum(1)
    val A1=w1.union(w2).keyBy(0).sum(1)
    val A2=A1.union(w3).keyBy(0).sum(1)
    A2
  }
}
