/*
import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._


import scala.collection.mutable
case class Histogram(height: Int, index: Int)
object Solution {

  // Complete the largestRectangle function below.
  def largestRectangle(h: Array[Int]): Long = {
    val histogram = h
    //val histogram = Seq(5, 4)
    var currentArea = 0
    var maxArea = 0
    val stack = new mutable.Stack[Histogram]
    for (i <- histogram.indices) {
      while (stack.nonEmpty && stack.top.height > histogram(i)) {
        val hist = stack.pop
        currentArea = (i - 1 - (if (stack.isEmpty) -1 else stack.top.index)) * hist.height
        if (currentArea > maxArea)
          maxArea = currentArea
      }
      stack.push(Histogram(histogram(i), i))
    }
    while (stack.nonEmpty) {
      val hist = stack.pop
      currentArea = (histogram.length - 1 - (if (stack.isEmpty) -1
                                             else
                                               stack.top.index)) * hist.height
      if (currentArea > maxArea)
        maxArea = currentArea
    }

    maxArea

  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = stdin.readLine.trim.toInt

    val h = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = largestRectangle(h)

    printWriter.println(result)

    printWriter.close()
  }
}
*/
