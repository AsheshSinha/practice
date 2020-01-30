package com.prac.basics.tree

import scala.collection.mutable

object ZigzagTraversal {
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
  }
  def printZigzag(tree: Option[Node[Int]]): Unit = {
    val currentLevel = new mutable.Stack[Option[Node[Int]]]
    val nextLevel = new mutable.Stack[Option[Node[Int]]]
    var isFromLeft = true
    var node = tree
    var currentStack = currentLevel
    while (currentStack.nonEmpty) {
      node = currentStack.pop
      if(isFromLeft){
        currentStack.push(node.get.left)
        currentStack.push(node.get.right)
      }else{
        currentStack.push(node.get.right)
        currentStack.push(node.get.left)
      }
      println(node.get.value)

    }

  }

}
