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
    "org.apache.spark" %% "spark-hive" % sparkVersion
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

fork in run := true
javaOptions in run ++= Seq(
  "-Dlog4j.configuration=log4j.properties")
fork in Test := true
javaOptions in Test ++= Seq(
  "-Dlog4j.configuration=log4j.properties")

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in(Compile, run))
