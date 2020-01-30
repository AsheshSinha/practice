package com.prac.basics.stack

import scala.collection.mutable

case class Histogram(height: Int, index: Int)

object LargestRectangleHistogram {
  def main(args: Array[String]): Unit = {
    val histogram = Seq(2, 1, 5, 6, 2)
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
      currentArea = (histogram.length - 1 - (if (stack.isEmpty) -1 else stack.top.index)) * hist.height
      if (currentArea > maxArea)
        maxArea = currentArea
    }

    println(maxArea)
  }
}
