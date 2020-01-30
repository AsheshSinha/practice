package com.prac.basics.tree

import scala.collection.mutable

object MaxSumLevel {
  def main(args: Array[String]): Unit = {
    val tree = Some(
      Node[Int](
        1,
        Some(
          Node[Int](
            2,
            Some(Node[Int](4, Some(Node[Int](10)), Some(Node[Int](11)))),
            Some(Node[Int](5))
          )
        ),
        Some(Node[Int](3, Some(Node[Int](6)), Some(Node[Int](7))))
      )
    )
    println(findLevelWithMaxSum(tree))
  }

  def findLevelWithMaxSum(tree: Option[Node[Int]]): (Int, Int) = {
    var node = tree
    var level = 0
    var currentSum = 0
    var maxSum = 0
    var levelWithMaxSum = 0
    val queue = new mutable.Queue[Option[Node[Int]]]
    queue.enqueue(node)
    queue.enqueue(None)
    while (queue.nonEmpty) {
      node = queue.dequeue
      if (node.isEmpty) {
        if (currentSum > maxSum) {
          maxSum = currentSum
          levelWithMaxSum = level
        }
        level = level + 1
          currentSum = 0
        if (queue.nonEmpty)
          queue.enqueue(None)

      } else {
        currentSum = currentSum + node.get.value
        if (node.get.left.isDefined) {
          queue.enqueue(node.get.left)
        }
        if (node.get.right.isDefined) {
          queue.enqueue(node.get.right)
        }
      }
    }
    (levelWithMaxSum, maxSum)
  }
}
