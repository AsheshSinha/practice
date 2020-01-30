package com.prac.basics.stack

import java.io.PrintWriter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object PoisonousPlantProblem {

  // Complete the poisonousPlants function below.
  def poisonousPlants(p: Array[Int]): Int = {
    var plants = p
    var days = 0
    var loop = true
    while (loop) {
      loop = false
      val stack = new mutable.Stack[Int]
      val out = ArrayBuffer.empty[Int]
      for (i <- plants.indices) {
        if (stack.isEmpty || plants(i) <= plants(stack.top)) {
          out += plants(i)
        } else {
          loop = true
        }
        if (plants(i) != 0)
          stack.push(i)
      }
      days = days + 1
      plants = out.toArray
    }
    days - 1
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = stdin.readLine.trim.toInt

    val p = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = poisonousPlants(p)

    printWriter.println(result)

    printWriter.close()
  }
}
