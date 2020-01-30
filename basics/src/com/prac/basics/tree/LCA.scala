package com.prac.basics.tree

/**
  * Least Common ancestor
  */
object LCA {
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
    println(findLCA(tree, 4, 5 ).get.value)
  }

  def findLCA(tree: Option[Node[Int]], a: Int, b: Int): Option[Node[Int]] = {
    if (tree.isEmpty) {
      None
    } else if (tree.get.value == a || tree.get.value == b) {
      tree
    } else {
      val left = findLCA(tree.get.left, a, b)
      val right = findLCA(tree.get.right, a, b)
      if (left.isDefined && right.isDefined) {
        tree
      } else if (left.isEmpty)
        right
      else
        left
    }

  }
}
