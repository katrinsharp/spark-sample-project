package sample

import com.typesafe.config.ConfigFactory
import org.apache.spark.streaming.Seconds
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.collection.mutable

class HelloLocalSparkSpec extends WordSpecLike
  with Matchers
  with BeforeAndAfterAll
  with Eventually {

  val conf = ConfigFactory.load()

  lazy val ssc = Utils.createSparkStreamingContext(conf, Seconds(10))

  override protected def afterAll(): Unit = {
    super.afterAll()
    ssc.stop()
  }

  "Hello Spark Streaming" should {

    "successfully receives multiple batches" in {

      val items = mutable.Map.empty[Int, Long].withDefaultValue(0L)

      val stream = ssc.receiverStream(new CryptoCurrencyFeedReceiver())

      stream.foreachRDD(rdd => items += rdd.id -> (items(rdd.id) + rdd.count()))

      ssc.start()

      Thread.sleep(22000)

      println(items)

      assert(items.exists {
        case (_, trades) => trades > 0
      })

    }
  }
}

