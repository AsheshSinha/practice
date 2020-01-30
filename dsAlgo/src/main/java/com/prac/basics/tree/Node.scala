package com.prac.basics.tree

case class Node[T] (value: T, var left: Option[Node[T]] = None, var right: Option[Node[T]] = None)
