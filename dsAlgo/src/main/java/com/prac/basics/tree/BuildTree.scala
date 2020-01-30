/*
package com.prac.basics.tree

object BuildTree {
  def main(args: Array[String]): Unit = {
    val arr: Seq[Char] = Seq('I', 'L', 'I', 'L', 'L') //preorder, L = leaf node, I = internal node
  }

  private def buildTree(arr: Seq[Char]): Unit = {
    var isLeftTree = true
    var tree: Option[Node[Char]] = Some(Node(arr(0)))

    for (i <- arr.indices) {
      if (i > 0) {
        if (isLeftTree) {
          if(arr(i) == 'L'){
            tree.get.left = Some(Node(arr(i)))
          }
          else{
            tree.get.left = Some(Node(arr(i)))
            tree = tree.left
          }

          isLeftTree = false
        }else {
            tree.right = Some(Node(arr(i)))
            isLeftTree = true
        }
      }
    }
  }
}
*/
