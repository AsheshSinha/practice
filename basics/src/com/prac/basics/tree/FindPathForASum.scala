package com.prac.basics.tree
object FindPathForASum {
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
    println(findPathForASum(tree, 5))
  }

  def findPathForASum(tree: Option[Node[Int]], sum: Int): Boolean = {

    if (sum == 0)
      true
    else if (tree.isEmpty)
      false
    else {
      val remainingSum = sum - tree.get.value

      if (tree.get.left.isDefined && tree.get.right.isDefined)
        findPathForASum(tree.get.left, remainingSum) || findPathForASum(
          tree.get.right,
          remainingSum
        )
      else if (tree.get.left.isDefined)
        findPathForASum(tree.get.left, remainingSum)
      else
        findPathForASum(tree.get.right, remainingSum)
    }
  }
}
