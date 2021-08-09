package Chapter7

//import scala.sys.error

/*********************************************************************
 * Chapter 7 Case Classes and Pattern Matching
 *********************************************************************/
object Ch7{
  def main(args: Array[String]): Unit = {
    //val n = eval(new Sum(new Number(1),new Number(2)))
    //println(n)
    val total = Sum(Number(2),Number(3))
    total.print()
    println(s" = ${total.eval.toString}")
  }
  /*
   * Say we want to write an interpreter for arithmetic expressions.
   * Just a simple one for + operations and numbers.
   * We can represent such expressions with a class hierarchy rooted
   * at abstract class Expr and two sub classes Number and Sum.
   * Then the expression 1 + (3 + 7) would be represented as:
   * new Sum(new Number(1), new Sum(new Number(3), new Number(7))).
   *
   */
  /*
   * // commented out to demonstrate a decomposed version

  abstract class Expr {
    def isNumber: Boolean
    def isSum: Boolean
    def numValue: Int
    def leftOp: Expr
    def rightOp: Expr
  }
  class Number(n: Int) extends Expr {
    def isNumber: Boolean = true
    def isSum: Boolean = false
    def numValue: Int = n
    def leftOp: Expr = error("Number.leftOp")
    def rightOp: Expr = error("Number.rightOp")
  }
  class Sum(e1: Expr, e2: Expr) extends Expr {
    def isNumber: Boolean = false
    def isSum: Boolean = true
    def numValue: Int = error("Sum.numValue")
    def leftOp: Expr = e1
    def rightOp: Expr = e2
  }
  def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else error("unrecognized expression kind")
  }
  def print(e: Expr) {
    if (e.isNumber) Console.print(e.numValue)
    else if (e.isSum) {
      Console.print("(")
      print(e.leftOp)
      Console.print("+")
      print(e.rightOp)
      Console.print(")")
    } else error("unrecognized expression kind")
  }
  */

  /*
  * The above program would be tedious if we need to add another method
  * such as prod for Product. We can reuse existing, unmodified code
  * through inheritance. We can do this by making the "high-level"
  * eval into a method of each expression class instead of implementing
  * it as a function outside of the expression class hierarchy.
  *
  * The idea is that object oriented decomposition is preferable for
  * constructing systems that should be extensible for new types
  * of data.
  *
  * Just like the eval external function, the print function can
  * also be decomposed and implemented into each class node.
  * When a system is extended with new operations, all classes must
  * be modified.
  *
  */

  abstract class Expr{
    def eval: Int
    def print(): Unit
  }
  /*
   * Without the "case" we would need to create object using "new"
   * with the case class, a constructor function is added implicitly
   * i.e. def Number(n: Int) = new Number(n)
   */
  case class Number(n: Int) extends Expr{
    def eval: Int = n
    def print(): Unit = { Console.print(n) }
  }
  case class Sum(e1: Expr, e2: Expr){
    def eval: Int = e1.eval + e2.eval
    def print(): Unit = {
      Console.print("(")
      Console.print(e1.eval)
      Console.print("+")
      Console.print(e2.eval)
      Console.print(")")
    }
  }
}