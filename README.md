## Setup instructions:

* Install JDK: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html (JDK 8).
* Install sbt: 
Mac: http://www.scala-sbt.org/release/docs/Installing-sbt-on-Mac.html (you can use `brew`, it will install the latest).
Win: http://www.scala-sbt.org/0.13/docs/Installing-sbt-on-Windows.html
* Install Intellij: 
Mac: https://www.jetbrains.com/idea/download/#section=mac
Win: https://www.jetbrains.com/idea/download/#section=windows
* Install Scala plugin for IJ: Intellij -> Preferences -> Plugins -> Search for official Scala plugin.
* Clone this repo: `git clone https://github.com/katrinsharp/spark-sample-project.git`

## Verify

* Open up a CLI (command line) and `cd` to root folder of this repo. Type:

```
> sbt
```
You should see `>` after it's updating some its dependencies (Use `exit` for quitting). Now type:

```
> console
```

You should see `scala>` after some more updates (Use `:q` for quitting). You're ready to go with Scala CLI.

* Open the cloned project in IJ: File -> New -> Project from existing sources. You're ready to go with IJ.


# Different ways to execute the code

`sbt "testOnly sample.<name-of-the-test>"` - executes specific test

`sbt run` - executing Main with Spark in-memory

`sbt assembly` - creates a self-contained jar: `spark-sample-project/target/scala-2.11/spark-sample-project-assembly-0.1.jar` that can be submitted to Spark cluster
