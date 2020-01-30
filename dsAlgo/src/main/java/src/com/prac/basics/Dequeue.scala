package com.prac.basics

class Dequeue[T] {
  var front: Option[Node[T]] = None
  var rear: Option[Node[T]] = None
  var size = 0

  def pushFront(value: T): Unit = {
    val node = Node(value)
    if (front.isEmpty) {
      front = Some(node)
      rear = Some(node)
      front.get.previous = rear
      rear.get.next = front
      size = size + 1
    }
    else {
      node.next = front
      node.previous = front.get.previous
      front.get.previous = Some(node)
      size = size + 1
    }
  }

  def pushRear(value: T): Unit = {
    val node = Node(value)
    if (rear.isEmpty) {
      front = Some(node)
      rear = Some(node)
      front.get.previous = rear
      rear.get.next = front
      size = size + 1
    }
    else {
      node.next = front
      node.previous = rear
      rear.get.next = Some(node)
      size = size + 1
    }
  }

  def popFront: T = {
    if (front.isEmpty) {
      throw new Exception("Underflow")
    }
    val res = front.get.value
    if (front.get.next.isDefined) {
      front.get.next.get.previous = front.get.previous
      front = front.get.next
      size = size - 1
    }
    res
  }

  def popRear: T = {
    if (rear.isEmpty) {
      throw new Exception("Underflow")
    }
    val res = rear.get.value
    if (rear.get.previous.isDefined) {
      rear.get.previous.get.next = rear.get.next
      rear = rear.get.previous
      size = size - 1
    }
    res
  }

  def frontValue: Option[T] = {
    front
      .map(_.value)
  }

  def rearValue: Option[T] = {
    rear
      .map(_.value)
  }

  def dequeueSize: Int = size

  def isEmpty: Boolean = size == 0 || front.isEmpty && rear.isEmpty

}
