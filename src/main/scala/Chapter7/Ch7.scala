package Chapter7

import scala.sys.error

/*********************************************************************
 * Chapter 7 Case Classes and Pattern Matching
 *********************************************************************/
object Ch7{
  def main(args: Array[String]): Unit = {

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
}