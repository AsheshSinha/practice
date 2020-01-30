package com.prac.basics.tree

import scala.collection.mutable
import scala.util.control.Breaks._

object TreeTraversals {
  def main(args: Array[String]): Unit = {
    val tree = Node[Int](
      1,
      Some(Node[Int](2, Some(Node[Int](4)), Some(Node[Int](5)))),
      Some(Node[Int](3, Some(Node[Int](6)), Some(Node[Int](7))))
    )
    print("PreOrder:   ")
    preOrderTraversal(Some(tree))
    println
    print("InOrder:    ")
    inOrderTraversal(Some(tree))
    println
    print("PostOrder:  ")
    postOrderTraversal(Some(tree))
    println
    print("LevelOrder: ")
    levelOrderTraversal(Some(tree))
    println
    print("RLevelOrder: ")
    reverseLevelOrderTraversal(Some(tree))
  }

  def preOrderTraversal(tree: Option[Node[Int]]): Unit = {
    var node = tree
    val stack = new mutable.Stack[Option[Node[Int]]]
    breakable {
      while (true) {
        while (node.isDefined) {
          stack.push(node)
          print(node.get.value)
          print(" ")
          node = node.get.left
        }
        if (stack.isEmpty) break
        node = stack.pop
        node = node.get.right
      }
    }
  }

  def inOrderTraversal(tree: Option[Node[Int]]): Unit = {
    var node = tree
    val stack = new mutable.Stack[Option[Node[Int]]]
    breakable {
      while (true) {
        while (node.isDefined) {
          stack.push(node)
          node = node.get.left
        }
        if (stack.isEmpty) break
        node = stack.pop
        print(node.get.value)
        print(" ")
        node = node.get.right
      }
    }
  }

  def postOrderTraversal(tree: Option[Node[Int]]): Unit = {
    var node = tree
    var prev: Option[Node[Int]] = None
    val stack = new mutable.Stack[Option[Node[Int]]]
    breakable {
      while (true) {
        while (node.isDefined) {
          stack.push(node)
          node = node.get.left
        }
        if (stack.isEmpty) break

        if (stack.top.get.right.isEmpty || prev == stack.top.get.right) {
          node = stack.pop
          print(node.get.value)
          print(" ")
          prev = node
          node = None
        } else
          node = stack.top.get.right
      }
    }
  }

  def levelOrderTraversal(tree: Option[Node[Int]]): Unit = {
    var node = tree
    val queue = new mutable.Queue[Option[Node[Int]]]
    queue.enqueue(node)
    breakable {
      while (queue.nonEmpty) {
        node = queue.dequeue
        print(node.get.value)
        print(" ")
        if (node.get.left.isDefined) {
          queue.enqueue(node.get.left)
        }
        if (node.get.right.isDefined) {
          queue.enqueue(node.get.right)
        }
      }
    }
  }

  def reverseLevelOrderTraversal(tree: Option[Node[Int]]): Unit = {
    var node = tree
    val queue = new mutable.Queue[Option[Node[Int]]]
    val stack = new mutable.Stack[Option[Node[Int]]]
    queue.enqueue(node)

    while (queue.nonEmpty) {
      node = queue.dequeue
      if (node.get.right.isDefined) {
        queue.enqueue(node.get.right)
      }
      if (node.get.left.isDefined) {
        queue.enqueue(node.get.left)
      }
      stack.push(node)
    }
    while (stack.nonEmpty) {
      node = stack.pop
      print(node.get.value)
      print(" ")
    }
  }

}
