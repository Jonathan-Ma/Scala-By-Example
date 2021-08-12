package Chapter8

import scala.sys.error

object Ch8 {
  def main(args: Array[String]): Unit = {
    val x = new EmptyStack[Int]
    val y = x.push(1).push(2)
    val z = x.push(1).push(2)

    println(y.pop.top)
    println(isPrefix(z,y))
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
   * It is also possible to parameterize methods with types.
   * An example is a generic method that determines if one stack
   * is a prefix of another:
   */
  def isPrefix[A](a: Stack[A], b: Stack[A]) :Boolean = {
    a.isEmpty || a.top == b.top && isPrefix(a.pop, b.pop)
  }
  /*
   * The method parameters are called polymorphic which is
   * greek for "have many forms".
   *              -------------------
   * Local type inferences: when we create an object with generic
   * parameters such as: isPrefix[int] = (x, y) we can omit
   * the [int] because scala inferences the type by the expected
   * result or the functions value parameters. Polymorphic
   * functions can omit the type.
   */
  /*******************************************************************
   * 8.1 Type parameter bounds
   ******************************************************************/

}
