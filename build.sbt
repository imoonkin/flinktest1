name := "test1"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq("org.apache.flink" %% "flink-scala" % "1.7.2",
  "org.apache.flink" %% "flink-clients" % "1.7.2",
  "org.apache.flink" %% "flink-streaming-scala" % "1.7.2",
  "org.apache.kafka" %% "kafka" % "2.2.0"
)
