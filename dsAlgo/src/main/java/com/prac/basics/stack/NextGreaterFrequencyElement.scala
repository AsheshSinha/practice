package com.prac.basics.stack

import scala.collection.mutable

object NextGreaterFrequencyElement {
  def main(args: Array[String]): Unit = {
    //val input = Seq(1, 3, 7, 2, 5, 1, 4, 2, 1, 5)
    val noOfTestCase = scala.io.StdIn.readLine()
    for(i <- 0 until noOfTestCase.toInt){
      scala.io.StdIn.readLine()
      println()
      findNextGreaterFrequency(scala.io.StdIn.readLine().split(" ").map(_.toInt))

    }

  }

  private def findNextGreaterFrequency(input: Seq[Int]): Unit = {
    val frequencyTable = new mutable.HashMap[Int, Int]
    val stack = new mutable.Stack[Int]
    for (i <- input.indices) {
      val inputI = input(i)
      if (frequencyTable.contains(inputI)) {
        val freq = frequencyTable(inputI) + 1
        frequencyTable.update(inputI, freq)
      }
      else {
        frequencyTable.put(inputI, 1)
      }
    }
    var res = new Array[Int](input.size)
    for (i <- input.indices) {
      val inputI = input(i)
      if (i == 0 || frequencyTable(input(stack.top)) > frequencyTable(inputI)) {
        stack.push(i)
      }
      while (stack.nonEmpty && frequencyTable(input(stack.top)) < frequencyTable(inputI)) {
        res(stack.pop) = inputI
      }
      stack.push(i)
    }

    while (stack.nonEmpty) {
      res(stack.pop) = -1
    }
    res.foreach(t => {
      print(t)
      print(' ')
    })
  }
}
