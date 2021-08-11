package Chapter8

import scala.sys.error

object Ch8 {
  def main(args: Array[String]): Unit = {

  }
  // Scala classes can have type parameters
  abstract class Stack[A] {
    def push(x: A): Stack[A] = new NonEmptyStack[A](x, this)
    def isEmpty: Boolean
    def top: A
    def pop: Stack[A]
  }
  class EmptyStack[A] extends Stack[A] {
    def isEmpty = true
    def top = error("EmptyStack.top")
    def pop = error("EmptyStack.pop")
  }
  class NonEmptyStack[A](elem: A, rest: Stack[A]) extends Stack[A] {
    def isEmpty = false
    def top = elem
    def pop = rest
  }
  /*
   * The 'A' in above program is a type parameter of class stack
   * and its subclasses. They are arbitrary names and are enclosed
   * in brackets instead of parentheses to distinguish from value
   * parameters.
   *
   */
}
