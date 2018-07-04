import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

class WordCountTest extends FunSuite with BeforeAndAfterAll {

  private var sparkConf: SparkConf = _
  private var sc: SparkContext = _

  override def beforeAll() {
    sparkConf = new SparkConf().setAppName("unit-testing").setMaster("local")
    sc = new SparkContext(sparkConf)
  }

  test("get word count rdd") {
    val result = Main.countWords(sc, "src/main/resources/shakespeare.txt")
    assert(result.count() === 67109)
  }
  override def afterAll() { sc.stop()}
}
