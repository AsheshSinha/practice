import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.collection.mutable
import scala.collection.parallel.immutable._
import scala.collection.parallel.mutable._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object Result {

  /*
   * Complete the 'stockmax' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts INTEGER_ARRAY prices as parameter.
   */

  def stockmax(prices: Array[Int]): Long = {
    val stocks = prices
    val stack = new mutable.Stack[Int]
    var index = 0
    val out = new Array[Int](stocks.length)
    var maxProfit = 0
    for (i <- stocks.size -1 to 0 by -1) {
      while (stack.isEmpty || stocks(i) > stocks(stack.top)) {
        stack.push(i)
      }
      index = stack.top
      val span =  index - i
      out(i) = span
      maxProfit = maxProfit + (stocks(i + span) - stocks(i))
    }
    maxProfit
  }

}

object HRStockMaxProfit {
  def main(args: Array[String]) {
    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val t = StdIn.readLine.trim.toInt

    for (tItr <- 1 to t) {
      val n = StdIn.readLine.trim.toInt

      val prices =
        StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
      val result = Result.stockmax(prices)
      println(result)
      //printWriter.println(result)
    }

    //printWriter.close()
  }
}
