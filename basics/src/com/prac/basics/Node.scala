package com.prac.basics

case class Node[T](value: T, var previous: Option[Node[T]] = None, var next: Option[Node[T]] = None)
