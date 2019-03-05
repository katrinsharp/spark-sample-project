package sample

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class WordCountTest extends FunSuite with BeforeAndAfterAll {

  lazy val sparkConf: SparkConf = new SparkConf().setAppName("unit-testing").setMaster("local")
  lazy val sc: SparkContext = new SparkContext(sparkConf)

  override def beforeAll() {

  }

  test("get word count rdd") {
    val result = Main.countWords(sc, "src/main/resources/shakespeare.txt")
    assert(result.count() === 67109)
  }
  override def afterAll() { sc.stop()}
}
