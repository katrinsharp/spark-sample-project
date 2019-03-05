name := "spark-sample-project"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val sparkVersion = "2.1.0"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % "provided" withSources(),
    "com.holdenkarau" %% "spark-testing-base" % (sparkVersion + "_0.6.0") % "test" excludeAll(
      ExclusionRule(organization = "org.scalacheck"),
      ExclusionRule(organization = "org.scalactic"),
      ExclusionRule(organization = "org.scalatest")
    ),
    "org.apache.spark" %% "spark-hive" % sparkVersion,
    "org.apache.spark" % "spark-streaming_2.11" % sparkVersion % "provided",
    "com.typesafe" % "config" % "1.3.1",
    "org.jfarcand" % "wcs" % "1.5"
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

fork in run := true
javaOptions in run ++= Seq(
  "-Dlog4j.configuration=log4j.properties")
fork in Test := false
javaOptions in Test ++= Seq(
  "-Dlog4j.configuration=log4j.properties")

parallelExecution in Test := false

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in(Compile, run))
