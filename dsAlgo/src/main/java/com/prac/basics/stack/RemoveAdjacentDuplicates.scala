package com.prac.basics.stack

import scala.collection.mutable.Stack

object RemoveAdjacentDuplicates{
  def main(args: Array[String]): Unit = {
    val str1 = "mississippi"
    val stack = new Stack[Char]()

    val sb = new StringBuilder
    for (i <- str1.indices){
      val x =  str1.charAt(i)
      if(stack.isEmpty || stack.top != x)
        stack.push(x)
      else
        stack.pop
    }
    println(stack.reverse)
  }
}
