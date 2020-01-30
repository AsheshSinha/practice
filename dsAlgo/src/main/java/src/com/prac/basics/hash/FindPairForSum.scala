package com.prac.basics.hash

import scala.collection.mutable

object FindPairForSum {
  def main(args: Array[String]): Unit = {
    val a = Seq(1,4,7,9,8,11)
    val b = Seq(9,1,6,7,3,7)
    val hashSet = new mutable.HashSet[Int]
    val input = b
    val sum = 14
    var res= false
    for(i <- input.indices){
      if(hashSet.contains(input(i)))
        res = true
      else{
        hashSet.add(sum - input(i))
      }
    }
    println(res)
  }
}
