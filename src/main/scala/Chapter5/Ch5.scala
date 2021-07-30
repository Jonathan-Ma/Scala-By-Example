package Chapter5
import scala.math._
object Ch5 {
  def main(args: Array[String]): Unit = {
    println()
    println("The 2 numbers will be 1 and 5")
    println("Sum of integers between the two: " + sumInts(1, 5))
    println("Sum of the squares of all integers between the two: " + sumSquare(1, 5))
    println("Sum of the powers 2^n of all n between the two: " + sumPower(1, 5))
    println()
    println("Higher order version of sum ints: " + sumInts(1, 5))
    println("Higher order square: " + sumSquare2(1,5))
    println("Higher order power: " + sumPower(1, 5))
    println()
    println("Currying version sumInts: " + sumInts3(1, 5))
    println("Currying version sumSquares: " + sumSquare3(1, 5))
    println()
    println("Tail recursion version sum ints: " + sum4(1, 5))
    println()
    println("Function that computes function product: " + f(1, 5))
  }

  /*
     * Functions in scala is a "first-class value", they can be
     * passed in as parameters of another function or they can
     * evaluate to a result. Functions that take in another
     * function as a parameter are called higher-order functions.
     *
     */
  // function to add integers between a and b
  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInts(a + 1, b)

  // function to add the square of all integers between a and b
  def square(x: Int):Int = x * x
  def sumSquare(a: Int, b: Int): Int =
    if (a > b) 0 else square(a) + sumSquare(a + 1, b)

  // function that adds the powers 2^n of n integers between a and b
  def power(x: Int): Int = if(x == 0) 1 else pow(2, x).toInt:  Int
  def sumPower(a:Int, b:Int): Int = if (a > b) 0 else power(a) + sumPower(a + 1, b): Int

  /*
   * the functions above can all factor out a "sum" function
   * and we can make it into a higher order function by
   * passing in another function.
   *
   */
  // a sum function that takes is another function f.
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)

  // sum of ints using sum functions
  def sumInts2(a: Int, b: Int): Int = sum(id, a, b)
  def id(x: Int): Int = x

  //sum of power using higher order
  def sumPower2(a: Int, b: Int): Int = sum(power2, a, b)
  def power2(x: Int): Int = if(x == 0) 1 else pow(2, x).toInt:  Int

  //sum of squares of all ints
  def sumSquare2(a: Int, b: Int): Int = sum(square, a, b)
  def square2(x: Int): Int = x * x

  /*
   * id, powerNew, and sqr are argument functions which we can omit
   * the name, functions without names are called anonymous
   * functions.
   *
   */
  // The id function can be defined like so:
  // (x: Int) => x * x
  // Therefore, we can write the sumInts function like this:
  // sumInts(a: Int, b: Int): Int = sum((x: Int) => x * x, a, b)

  /*
   * Often, scala can deduce the parameter types from the context of
   * anonymous functions in which case they can be omitted.
   * from the first type of sum we know that it takes in a function
   * of type Int => Int, hence, the parameter type for "(x: Int)"
   * is redundant. Also, a single parameter without a type can
   * omit the parentheses.
   *
   */
    // Therefore, we can write the sumInts and sumSquare2 like this:
    // sumInts(a: Int, b: Int): Int = sum(x => x, a, b)
    // sumSquare2(a: Int, b: Int): Int = sum(x => x * x, a, b)
  /*
   * We can say anonymous functions are "syntactic sugar" meaning
   * they make things more concise, convenient and readable for
   * humans
   */

  /*********************************************************************************************
   *
   * 5.2 Currying
   *
   **********************************************************************************************/

   /*
    * The higher order version of using functions are compact,
    * but we can do even better! a and b show up a lot in every function
    * so we can get minimize them from appearing so often
    */
    // sum function that does not take bounds a and b:
    def sum2(f: Int => Int): (Int, Int) => Int = {
     def sumF (a: Int, b: Int): Int =
       if(a > b) 0 else f(a) + sumF(a + 1, b)
     sumF
   }
    // Then the auxiliary functions can be defined like so:
    def sumInts3 = sum2(x => x)
    def sumSquare3 = sum2(x => x * x)

    //tail recursion of sum ints
    def sumInts4(f: Int => Int)(a: Int, b:Int): Int = {
      def iter(a: Int, result: Int): Int = {
        if(a > b) result
        else iter(a + 1, result + a)
      }
      iter(a, 0)
    }
  def sum4 = sumInts4(x => x)(_, _)

    // Function product that computes the product of the values of
    // functions at points over a given range.
    def f = product(x => x + 4)(x => x + 3)(_,_)
    def product(f: Int => Int)(g: Int => Int)(a: Int, b: Int): Int = {
      def compute(a: Int, result: Array): Int = {
        if (a > b) result
        else {
          print(f(a) * g(a) + " ")
          compute(a + 1, result(a) = (f(a) * g(a)) )
        }
      }
      compute(a, array[])
    }
}
