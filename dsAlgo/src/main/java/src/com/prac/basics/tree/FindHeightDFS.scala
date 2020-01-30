package com.prac.basics.tree

object FindHeightDFS {
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
    println(findHeight(tree))
  }
  def findHeight(tree: Option[Node[Int]]): Int = {
    var lHeight = 0
    var rHeight = 0
    if (tree.get.left.isEmpty && tree.get.right.isEmpty) {
      0
    }
    if (tree.get.left.isDefined) {
      lHeight = 1 + findHeight(tree.get.left)
    }
    if (tree.get.right.isDefined) {
      rHeight = 1 + findHeight(tree.get.right)
    }
    if (rHeight > lHeight) {
      rHeight
    } else
      lHeight
  }

}
