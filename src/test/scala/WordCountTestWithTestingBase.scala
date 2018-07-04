import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.FunSuite

class WordCountTestWithTestingBase extends FunSuite with SharedSparkContext {

  test("get word count rdd USING SPARK_TEST_BASE") {
    val result =  Main.countWords(sc, "src/main/resources/shakespeare.txt")
    assert(result.count() === 67109)
  }
}

