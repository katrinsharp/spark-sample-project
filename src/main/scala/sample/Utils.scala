package sample

import com.typesafe.config.Config
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Duration, StreamingContext}

object Utils {

  def getOrCreateSparkSession(conf: Config) = {

    val appName = "Scala for Spark"
    val master = conf.getString("spark.master")
    // val jars = conf.getString("spark.jars.location")
    val session = SparkSession.builder
      .master(master)
      .appName(appName)
      // .config("spark.jars", jars)
      .getOrCreate()
    session
  }

  def createSparkStreamingContext(conf: Config, time: Duration) = {

    val appName = "Scala for Spark"
    val master = conf.getString("spark.master")
    val sparkConf = new SparkConf()
      .setMaster(master)
      .setAppName(appName)

    new StreamingContext(sparkConf, time)
  }
}
