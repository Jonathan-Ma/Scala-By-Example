package Chapter5
import scala.math._
object Ch5 {
  def main(args: Array[String]): Unit = {
    println()
    println("The 2 numbers will be 1 and 5")
    println("Sum of integers between the two: " + sumInt(1, 5))
    println("Sum of the squares of all integers between the two: " + sumSquare(1, 5))
    println("Sum of the powers 2^n of all n between the two: " + sumPower(1, 5))
    println("Higher order version of sum ints: " + sumInts(1, 5))
    println("Higher order square: " + sumSquare2(1,5))
    println("Higher order power: " + sumPower(1, 5))
    println()
  }

  /*
     * Functions in scala is a "first-class value", they can be
     * passed in as parameters of another function or they can
     * evaluate to a result. Functions that take in another
     * function as a parameter are called higher-order functions.
     *
     */
  // function to add integers between a and b
  def sumInt(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInt(a + 1, b)

  // function to add the square of all integers between a and b
  def square(x: Int):Int = x * x
  def sumSquare(a: Int, b: Int): Int =
    if (a > b) 0 else square(a) + sumSquare(a + 1, b)

  // function that adds the powers 2^n of n integers between a and b
  def power(x: Int): Int = if(x == 0) 1 else pow(2, x).toInt:  Int
  def sumPower(a:Int, b:Int): Int = if (a > b) 0 else power(a) + sumPower(a + 1, b): Int

  // the functions above can all factor out a sum function:
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)

  // sum of ints using sum functions
  def sumInts(a: Int, b: Int): Int = sum(id, a, b)
  def id(x: Int): Int = x

  //sum of power using higher order
  def sumPow(a: Int, b: Int): Int = sum(power, a, b)
  def powerNew(x: Int): Int = if(x == 0) 1 else pow(2, x).toInt:  Int

  //sum of squares of all ints
  def sumSquare2(a: Int, b: Int): Int = sum(sqr, a, b)
  def sqr(x: Int): Int = x * x
}
