import java.io.PrintWriter

import scala.collection.mutable

object EqualStackProblem {

  /*
   * Complete the equalStacks function below.
   */
  def equalStacks(h1: Array[Int],
                  n1: Int,
                  h2: Array[Int],
                  n2: Int,
                  h3: Array[Int],
                  n3: Int): Int = {
    val s1 = new mutable.Stack[Int]
    val s2 = new mutable.Stack[Int]
    val s3 = new mutable.Stack[Int]
    var maxHeight = 0
    val currentHeight = 0
    var i = n1 - 1
    var j = n2 - 1
    var k = n3 - 1
    var temp = 0
    while (i > 0 || j > 0 || k > 0) {
      if (s1.isEmpty) {
        s1.push(h1(i))
      }
      if (s2.isEmpty) {
        s2.push(h2(j))
      }
      if (s3.isEmpty) {
        s3.push(h3(k))
      }
      if (s1.top > s2.top)
        temp = s1.top
      else
        temp = s2.top
      if (temp < s3.top)
        temp = s3.top
      while (s1.top < temp && i > 0) {
        i = i - 1
        s1.push(s1.top + h1(i))
      }
      while (s2.top < temp && j > 0) {
        j = j - 1
        s2.push(s2.top + h2(j))
      }
      while (s3.top < temp && k > 0) {
        k = k - 1
        s3.push(s3.top + h3(k))
      }
      if (s1.top == s2.top && s2.top == s3.top) {
        if (s1.top > maxHeight)
          maxHeight = s1.top
        if (i > 0) {
          i = i - 1
          s1.push(s1.top + h1(i))
        }
        if (j > 0) {
          j = j - 1
          s2.push(s2.top + h2(j))
        }
        if (k > 0) {
          k = k - 1
          s3.push(s3.top + h3(k))
        }
      }
      if (i == 0 && j == 0 && k > 0) k = k - 1
      if (i == 0 && j > 0 && k == 0) j = j - 1
      if (i > 0 && j == 0 && k == 0) i = i - 1
      if (i == 0 && j > 0 && k > 0) {
        k = k - 1
        j = j - 1
      }
      if (i > 0 && j == 0 && k > 0) {
        k = k - 1
        i = i - 1
      }
      if (i > 0 && j > 0 && k == 0) {
        i = i - 1
        j = j - 1
      }

    }
    maxHeight
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    // val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n1N2N3 = stdin.readLine.split(" ")

    val n1 = n1N2N3(0).trim.toInt

    val n2 = n1N2N3(1).trim.toInt

    val n3 = n1N2N3(2).trim.toInt

    val h1 = stdin.readLine.split(" ").map(_.trim.toInt)

    val h2 = stdin.readLine.split(" ").map(_.trim.toInt)

    val h3 = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = equalStacks(h1, n1, h2, n2, h3, n3)
    println(result)
    //printWriter.println(result)

    //printWriter.close()
  }
}
