package com.prac.basics.tree

import scala.collection.mutable.ListBuffer

object PathTOLeaf {
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
    buildPathToLeaf(tree, new ListBuffer[Int])
  }
  def buildPathToLeaf(tree: Option[Node[Int]], elems: ListBuffer[Int]): Unit = {
    var test = new ListBuffer[Int] ++ elems
    if (tree.isEmpty) {
      println(test)
    } else {
      test += tree.get.value
      buildPathToLeaf(tree.get.left, test)
      buildPathToLeaf(tree.get.right, test)
    }
  }
}
