package com.prac.basics.tree

object PrintAncestors {
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
    printAnc(tree, 7)
  }
  def printAnc(tree: Option[Node[Int]], a: Int): Boolean = {
    var isPrintLeft = false
    var isPrintRight = false
    var isPrint = false
    if (tree.isEmpty) {
      isPrint = false
    } else if (tree.get.value == a) {
      isPrint = true
    } else {
      isPrintLeft = printAnc(tree.get.left, a)
      isPrintRight = printAnc(tree.get.right, a)
    }
    if (isPrint || isPrintLeft || isPrintRight) {
      println(tree.get.value)
    }
    isPrint || isPrintLeft || isPrintRight
  }
}
