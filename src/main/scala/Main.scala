
import java.io.File

import org.apache.spark._

object Main extends App {

  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
  }

  def countWords(sc: SparkContext, inputFileName: String) = {

    val textFile = sc.textFile(inputFileName)

    //word count
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    System.out.println("Total words: " + counts.count())
    counts
  }

  //Create a SparkContext to initialize Spark
  val conf = new SparkConf().setAppName("Word Count").setMaster("local")
  val sc = new SparkContext(conf)

  val counts = countWords(sc, "src/main/resources/shakespeare.txt")

  val output = new File("/tmp/shakespeareWordCount")

  deleteRecursively(output)

  counts.saveAsTextFile(output.getAbsolutePath)
}
