package com.prac.basics.stack

import scala.collection.mutable

object StockSpanProblem {
  def main(args: Array[String]): Unit = {
    //val stocks = Seq(100, 80, 60, 70, 60, 75, 85)
    //val stocks = Seq(5,4,3,4,5)
    val stocks = Seq(1,2,3,4)
    val stack = new mutable.Stack[Int]
    var index = 0
    val out = new Array[Int](stocks.length)
    var maxProfit = 0
    for (i <- stocks.indices) {
      while (stack.nonEmpty && stocks(i) > stocks(stack.top)) {
        index = stack.pop
        //maxProfit = maxProfit + (stocks(i) - stocks(index))
      }
      if (stack.isEmpty)
        index = -1
      else
        index = stack.top
      val span = i - index
      if(span > 1) {
        for (j <- (i - span + 1) until i){
          maxProfit = maxProfit + (stocks(i) - stocks(j))
          println("i = " + i,stocks(i))
          println("j = "+ j,stocks(j))
          println()
        }


      }

      out(i) = span

      stack.push(i)
    }
    println(out.toSeq)
    println(maxProfit)
  }
}
